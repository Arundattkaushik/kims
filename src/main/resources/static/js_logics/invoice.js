$(document).ready(() => {
	$('.btn-status').prop('disabled', true);
	
	loadPartyDataInSession();
	loadCompanyDetails();
})

function loadCompanyDetails(){
	$.ajax({
		type: 'Get',
		url: '/get-company-details',
		success: function(data){	
			console.log(data);
			document.getElementById("bankName").innerHTML=data.bankName;
			document.getElementById("bankAccountNo").innerHTML=data.bankAccountNumber;
			document.getElementById("bankIFSC").innerHTML=data.bankIfscCode;
			document.getElementById("termsAndCondtions").innerHTML=data.termsAndConditions;
			document.getElementById("for-authorized-signature").innerHTML=data.companyName;
			document.getElementById("companyGstin").innerHTML=data.gstin;
			document.getElementById("companyMobile").innerHTML=data.companyContactNo;
			document.getElementById("companyName").innerHTML=data.companyName;
			document.getElementById("companyAddress").innerHTML=data.companyMailingAddress;
		},
		error: function(errorThrown) {
			console.log("Error block run for => loadCompanyDetails() " + errorThrown)
		}
	});
}



function setBillToDetails(){
	let billToParty = document.getElementById("select_billTo_party").value;
	$.ajax({
				type: 'Post',
				url: '/get-bill-to-party',
				data:{'pTitle':billToParty},			
				success: function(data, textStatus) {
					/*console.log("Success block: "+textStatus);*/ 
					document.getElementById("billToAddress").value=data.address;
					document.getElementById("billToState").value=data.state;
					document.getElementById("billToStateCode").value=data.stateCode;
					document.getElementById("billToGstInNo").value=data.gstin;

				},
				error: function(errorThrown) {
					console.log("Error block run for => setBillToDetails() " + errorThrown)
				}
		});	
}




function setShipToDetails(){
	
	let shipToParty = document.getElementById("select_shipTo_party").value;
		$.ajax({
					type: 'Post',
					url: '/get-ship-to-party',
					data:{'pTitle':shipToParty},			
					success: function(data, textStatus) {
						/*console.log("Success block: "+textStatus);*/ 
						document.getElementById("shippToAddress").value=data.address;
						document.getElementById("shippToState").value=data.state;
						document.getElementById("shippToStateCode").value=data.stateCode;
						document.getElementById("shippToGstInNo").value=data.gstin;

					},
					error: function(errorThrown) {
						console.log("Error block run for => setShipToDetails() " + errorThrown)
					}
			});	
	
}




function loadPartyDataInSession(){
	$.ajax({
			type: 'GET',
			url: '/get-master-avl-qty',			
			success: function(data, textStatus) {
				/*console.log("Success block: "+textStatus);*/

			},
			error: function(errorThrown) {
				console.log("Error block run for => loadPartyDataInSession() " + errorThrown)
			}
		});
}


/* Bill To, Ship To logic*/
function billToShipToCheckBox() {
	let chkbox = document.getElementById("billTo_shippTo_chk_box").checked;

	/* Bill To Details*/
	let billToName = document.getElementById("billToName").value;
	let billToNamefromlist = document.getElementById("select_billTo_party").value;
	
	
	let billToAddress = document.getElementById("billToAddress").value;
	let billToState = document.getElementById("billToState").value;
	let billToStateCode = document.getElementById("billToStateCode").value;
	let billToGstInNo = document.getElementById("billToGstInNo").value;


	if (chkbox == true) {
		if(document.getElementById("billTo_party_chk_box").checked==true){
			document.getElementById("shippToName").value = billToNamefromlist;
		}
		if(document.getElementById("billTo_party_chk_box").checked==false){
			document.getElementById("shippToName").value = billToName;
		}
		
		document.getElementById("shippToAddress").value = billToAddress;
		document.getElementById("shippToState").value = billToState;
		document.getElementById("shippToStateCode").value = billToStateCode;
		document.getElementById("shippToGstInNo").value = billToGstInNo;
		
		/*disable select checkbox from party for shipp to */
		document.getElementById("shipTo_party_chk_box").disabled = true;

	}
	if (chkbox == false) {
		document.getElementById("shippToName").value = '';
		document.getElementById("shippToAddress").value = '';
		document.getElementById("shippToState").value = '';
		document.getElementById("shippToStateCode").value = '';
		document.getElementById("shippToGstInNo").value = '';
		
		/*enable select checkbox from party for shipp to */
		document.getElementById("shipTo_party_chk_box").disabled = false;
	}

}


function billToParty() {
	let x = document.getElementById("billTo_party_chk_box").checked;

	if (x == true) {

		document.getElementById("select_billTo_party").style.display = "block";
		document.getElementById("billTo_name_span").style.display = "none";
		document.getElementById("billToName").style.display = "none";

	}
	if (x == false) {

		document.getElementById("billTo_name_span").style.display = "block";
		document.getElementById("billToName").style.display = "block";
		document.getElementById("select_billTo_party").style.display = "none";


	}

}


function shipToParty() {
	let x = document.getElementById("shipTo_party_chk_box").checked;

	if (x == true) {

		document.getElementById("select_shipTo_party").style.display = "block";
		document.getElementById("shipTo_name_span").style.display = "none";
		document.getElementById("shippToName").style.display = "none";

	}
	if (x == false) {

		document.getElementById("shipTo_name_span").style.display = "block";
		document.getElementById("shippToName").style.display = "block";
		document.getElementById("select_shipTo_party").style.display = "none";


	}

}



function isBillToAndShipToChecked() {
	let x = document.getElementById("billTo_party_chk_box").checked;
	let y = document.getElementById("shipTo_party_chk_box").checked;

	if (x == y == true) {
		document.getElementById("billTo_shippTo_chk_box").disabled = true;
	}
	if (x == false || y == false) {
		document.getElementById("billTo_shippTo_chk_box").disabled = false;
	}
}



function addRow() {

	/* cloning the whole row */
	var v = $('#trow').clone().appendTo('#tbody');


	/* clearing newly created input and textareas */
	$(v).find("input").val('');
	$(v).find("input").css('background', 'white');

}



function removeRow(e) {
	/* finding row index before removing it */
	subtractDeletedAmount(e);
	/* Recalculating GST if row is deleted */
	calGST();

	$(e).parent().parent().remove();
}






/* function to recalculate Total Amount if a raw is deleted */
function subtractDeletedAmount(e) {
	let index = $(e).parent().parent().index();
	let amt = document.getElementsByName("amount")[index].value;
	let fTotal = document.getElementById("fTotal").value;
	let newValue = fTotal - amt;
	document.getElementById("fTotal").value = newValue;
}





/* Checking we can create invoice for entered SKU quantity  */
function getMasterSkuQuantity(e) {
	let index = $(e).parent().parent().index();
	let qty = document.getElementsByName("qty")[index].value;
	let mSku = document.getElementsByName("masterSku")[index].value;

	/* making an ajax call to retreive master sku available quantiy from database by sending a request to API */
	$.ajax({
		type: 'POST',
		url: '/get-master-avl-qty',
		data: { 'title': mSku },
		success: function(data, textStatus, jqXHR) {
			/* console.log("Success block: "+data);  */


			if (qty > data) {
				document.getElementsByName("qty")[index].style.background = "red";
				/* $('.btn-status').prop('disabled', true); */
			}
			if (qty > 0 && qty <= data) {
				document.getElementsByName("qty")[index].style.background = "white";
			}
			if (qty <= 0) {
				document.getElementsByName("qty")[index].style.background = "yellow";
			}

			submitButtonStatus(e);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("Error block run for => getMasterSkuQuantity(e) " + errorThrown);
		}
	});

}

function submitButtonStatus() {
	let col = document.getElementsByName("qty");
	let count = 0;
	for (let x = 0; x < col.length; x++) {
		if (col[x].style.background == 'red' || col[x].style.background == 'yellow') {
			count++;
		}
	}
	if (count != 0) {
		$('.btn-status').prop('disabled', true);
	}
	else {
		$('.btn-status').prop('disabled', false);
	}
}



/* function called when any change is done in any price or quantity field */
function cal(e) {

	/* finding row index */
	let index = $(e).parent().parent().index();
	let amt = parseInt(document.getElementsByName("qty")[index].value) * parseInt(document.getElementsByName("price")[index].value);
	document.getElementsByName("amount")[index].value = amt;

	/* Checking whether enterd quantity is available  */
	getMasterSkuQuantity(e);
	/* Setting total  */
	setTotal();
	/* calculatin CGST on every change  */
	calGST();
}




/* Get Total */
function setTotal() {
	let sum = 0;
	let amounts = document.getElementsByName("amount");
	for (let i = 0; i < amounts.length; i++) {
		sum = sum + +(amounts[i].value);
	}
	document.getElementById("fTotal").value = sum;
}




/* Calculating CGST */
function calGST() {

	let fTotal = document.getElementById("fTotal").value;

	let fcgst = 0;
	let fsgst = 0;
	let figst = 0;
	fcgst = document.getElementById("fcgst").value;
	fsgst = document.getElementById("fsgst").value;
	figst = document.getElementById("figst").value;


	/*getting courier charges*/
	let courCharge = 0;
	courCharge = document.getElementById("courierCharges").value;

	let newCgst = (fcgst * fTotal) / 100;
	let newfSgst = (fsgst * fTotal) / 100;
	let newfIgst = (figst * fTotal) / 100;


	/*Setting GST values in their respective fields*/
	document.getElementById("fcgstAmt").value = newCgst;
	document.getElementById("fsgstAmt").value = newfSgst;
	document.getElementById("figstAmt").value = newfIgst;

	document.getElementById("fgTotal").value = (+(fTotal) + +(newCgst) + +(newfSgst) + +(newfIgst) + +(courCharge));

}



/* Setting description  */
function setDesc(e) {
	let index = $(e).parent().parent().index();
	let x = document.getElementsByName("masterSku")[index].value;

	/* making an ajax call to retreive data from database by sending a request to controller */
	$.ajax({
		type: 'POST',
		url: '/get-mSku-desc',
		data: { 'title': x },
		success: function(data) {
			/*console.log("Success block: "+data)	*/
			const y = data.split(",");

			document.getElementsByName("particulars")[index].value = y[0];
			document.getElementsByName("hsn")[index].value = y[1];
		},
		error: function(errorThrown) {
			console.log("Error block run for => setDesc(e) " + errorThrown)
		}
	});

	/* Calling this function here to get the latest product select from the sku list */
	getMasterSkuQuantity(e);
}


/* function to print invoice */
function goPrint() {
	window.print();
}
