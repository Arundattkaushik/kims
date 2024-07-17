$(document).ready(() => {
	$('.btn-status').prop('disabled', true);
	
	loadCompanyDetails();
	loadPartyList();
	loadMasterSkuList();
})

function loadCompanyDetails(){
	$.ajax({
		type: 'Get',
		url: '/company/detail',
		success: function(res){	
			/*console.log(res.data);*/
			document.getElementById("bankName").innerHTML=res.data.bankName;
			document.getElementById("hbankName").value=res.data.bankName;
			
			
			document.getElementById("bankAccountNo").innerHTML=res.data.bankAccountNumber;
			document.getElementById("hbankAccountNo").value=res.data.bankAccountNumber;
			
			document.getElementById("bankIFSC").innerHTML=res.data.bankIfscCode;
			document.getElementById("hbankIFSC").value=res.data.bankIfscCode;
			
			document.getElementById("termsAndCondtions").innerHTML=res.data.termsAndConditions;
			document.getElementById("htermsAndCondtions").value=res.data.termsAndConditions;
			
			document.getElementById("for-authorized-signature").innerHTML=res.data.companyName;
			document.getElementById("companyGstin").innerHTML=res.data.gstin;
			document.getElementById("hcompanyGstin").value=res.data.gstin;
			
			
			document.getElementById("udyamDl").innerHTML=res.data.hudyamDl;
			document.getElementById("udyamDl").value=res.data.hudyamDl;
			
			
			document.getElementById("companyMobile").innerHTML=res.data.companyContactNo;
			document.getElementById("hcompanyMobile").value=res.data.companyContactNo;
			
			
			document.getElementById("additionalContactNo").innerHTML=res.data.additionalContactNo;
			document.getElementById("hadditionalContactNo").value=res.data.additionalContactNo;
			
			document.getElementById("companyName").innerHTML=res.data.companyName;
			document.getElementById("hcompanyName").value=res.data.companyName;
			
			document.getElementById("companyAddress").innerHTML=res.data.companyMailingAddress;
			document.getElementById("hcompanyAddress").value=res.data.companyMailingAddress;
		},
		error: function(errorThrown) {
			console.log("Error block run for => loadCompanyDetails() " + errorThrown)
		}
	});
}


function loadPartyList(){
	let billTo = $('#select_billTo_party');
	let shipTo = $('#select_shipTo_party');
	$.ajax({
					type: 'Get',
					url: '/party/list',			
					success: function(res) {
						/*console.log("Success block: "+textStatus);*/ 
						for(let i =0; i<res.data.length; i++){							
							billTo.append('<option value="'+ res.data[i].id +'">' + res.data[i].name + "</option>");
							shipTo.append('<option value="'+ res.data[i].id +'">' + res.data[i].name + "</option>");
						}
						

					},
					error: function(errorThrown) {
						console.log("Error block run for => setBillToDetails() " + errorThrown)
					}
			});	
}


function setBillToDetails(){
	let billToParty = document.getElementById("select_billTo_party").value;
	
	$.ajax({
				type: 'Post',
				url: '/party/get',
				data:{'id':billToParty},			
				success: function(res) {
					/*console.log("Success block: "+textStatus);*/ 
					document.getElementById("billToAddress").value=res.data.address;
					document.getElementById("billToState").value=res.data.state;
					document.getElementById("billToStateCode").value=res.data.stateCode;
					document.getElementById("billToGstInNo").value=res.data.gstin;

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
					url: '/party/get',
					data:{'id':shipToParty},			
					success: function(res) {
						/*console.log("Success block: "+textStatus);*/ 
						document.getElementById("shippToAddress").value=res.data.address;
						document.getElementById("shippToState").value=res.data.state;
						document.getElementById("shippToStateCode").value=res.data.stateCode;
						document.getElementById("shippToGstInNo").value=res.data.gstin;

					},
					error: function(errorThrown) {
						console.log("Error block run for => setShipToDetails() " + errorThrown)
					}
			});	
	
}




/*function loadPartyDataInSession(){
	$.ajax({
			type: 'GET',
			url: '/get-master-avl-qty',			
			success: function(data) {
				console.log("Success block: "+textStatus);
				console.log("Success block:  loadPartyDataInSession()"+data);

			},
			error: function(errorThrown, xhr) {
				alert('Request Status: ' + xhr.status + ' Status Text: ' + xhr.statusText + ' ' + xhr.responseText);
				console.log("Error block run for => loadPartyDataInSession() " + errorThrown)
				
			}
		});
}*/


/* Bill To, Ship To logic*/
function billToShipToCheckBox() {
	let chkbox = document.getElementById("billTo_shippTo_chk_box").checked;

	/* Bill To Details*/
	let billToName = document.getElementById("billToName").value;
	let billToNamefromlist = $('#select_billTo_party option:selected').text();
	
	
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
		/*document.getElementById("billTo_name_span").style.display = "none";*/
		document.getElementById("billToName").style.display = "none";

	}
	if (x == false) {

		/*document.getElementById("billTo_name_span").style.display = "block";*/
		document.getElementById("billToName").style.display = "block";
		document.getElementById("select_billTo_party").style.display = "none";


	}

}


function shipToParty() {
	let x = document.getElementById("shipTo_party_chk_box").checked;

	if (x == true) {

		document.getElementById("select_shipTo_party").style.display = "block";
		/*document.getElementById("shipTo_name_span").style.display = "none";*/
		document.getElementById("shippToName").style.display = "none";

	}
	if (x == false) {

		/*document.getElementById("shipTo_name_span").style.display = "block";*/
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
	/* there is no use of this api now, just kept it for the sake of status button logic */
	$.ajax({
		type: 'POST',
		url: '/msku/avl-qty',
		data: { 'title': mSku },
		success: function(res) {
			/* console.log("Success block: "+data);  */

			
			if (qty > 0 ) {
				document.getElementsByName("qty")[index].style.background = "white";
			}
			if (qty <= 0) {
				document.getElementsByName("qty")[index].style.background = "yellow";
			}

			submitButtonStatus(e);

		},
		error: function(errorThrown) {
			console.log("Error block run for => getMasterSkuQuantity(e) " + errorThrown);
		}
	});

}

function submitButtonStatus() {
	let col = document.getElementsByName("qty");
	let count = 0;
	for (let x = 0; x < col.length; x++) {
		if (col[x].style.background == 'yellow') {
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
	document.getElementById("fcgstAmt").value = newCgst.toFixed(2);
	document.getElementById("fsgstAmt").value = newfSgst.toFixed(2);
	document.getElementById("figstAmt").value = newfIgst.toFixed(2);

	document.getElementById("fgTotal").value = (+(fTotal) + +(newCgst) + +(newfSgst) + +(newfIgst) + +(courCharge)).toFixed(2);

}



/*Loading list of master skus at page load*/
function loadMasterSkuList(){
	let msku = $('#masterSku');
	$.ajax({
		type: 'Get',
		url: '/msku/list',		
		success: function(res) {
			/*console.log("Success block: "+data)*/			
			for(let i =0; i<res.data.length; i++){	
				//console.log(res.data[i].mskuTitle)						
				msku.append('<option value="'+ res.data[i].id +'">' + res.data[i].mskuTitle + "</option>");				
			}
			
		},
		error: function(errorThrown) {
			console.log("Error block run for => setDesc(e) " + errorThrown)
		}
	});
	
}




/* Setting description  */
function selectMaster(e) {
	let index = $(e).parent().parent().index();
	let x = document.getElementsByName("masterSku")[index].value;
	
	/* making an ajax call to retreive data from database by sending a request to controller */
	$.ajax({
		type: 'Post',
		url: '/msku/get',
		data: { 'id': x },
		success: function(res) {
			/*console.log("Success block: "+data)*/

			document.getElementsByName("particulars")[index].value = res.data.mskuDesc;
			document.getElementsByName("hsn")[index].value = res.data.mhsn;
		},
		error: function(errorThrown) {
			console.log("Error block run for => setDesc(e) " + errorThrown)
		}
	});

	/* Calling this function here to get the latest product select from the sku list */
	getMasterSkuQuantity(e);
}


/*Auto resizing text/perticular text box*/
function autoResize(){
	let textarea = document.getElementById('particulars');
	textarea.addEventListener('input', () => {
	    textarea.style.height = 'auto'; // Reset the height
	    textarea.style.height = textarea.scrollHeight + 'px'; // Set to scroll height
	});
}

/* function to print invoice */
function goPrint() {
	window.print();
}
