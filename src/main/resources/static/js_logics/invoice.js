$(document).ready(()=>{
		$('.btn-status').prop('disabled', true);
	})
	
	function addRow(){
		
		/* cloning the whole row */
		var v = $('#trow').clone().appendTo('#tbody');
		
		
		/* clearing newly created input and textareas */
		$(v).find("input").val('');
		$(v).find("input").css('background','white');
		
	}
	
	
	
	
	
	function removeRow(e){
			/* finding row index before removing it */
			subtractDeletedAmount(e);
			/* Recalculating GST if row is deleted */
			calGST();
			
			$(e).parent().parent().remove();
    	}
	
	
	
	
	
	
	/* function to recalculate Total Amount if a raw is deleted */
	function subtractDeletedAmount(e){
		let index = $(e).parent().parent().index();
		let amt = document.getElementsByName("amount")[index].value;
		let fTotal = document.getElementById("fTotal").value;
		let newValue = fTotal-amt;
		document.getElementById("fTotal").value = newValue;
	}
	
	
	
	
	
	/* Checking we can create invoice for entered SKU quantity  */
	function getMasterSkuQuantity(e){
		let index = $(e).parent().parent().index();	
		let qty = document.getElementsByName("qty")[index].value;
		let mSku = document.getElementsByName("masterSku")[index].value;
		
		/* making an ajax call to retreive master sku available quantiy from database by sending a request to API */
		$.ajax({
	            type: 'POST',
	            url: '/get-master-avl-qty',
	            data: {'title':mSku},
	            success: function (data, textStatus ,jqXHR) {
	                /* console.log("Success block: "+data);  */	
	                
	                		
	                		if(qty>data){
	                			document.getElementsByName("qty")[index].style.background = "red";
	                			/* $('.btn-status').prop('disabled', true); */
	                		}
	                		if(qty>0 && qty<=data){
	                			document.getElementsByName("qty")[index].style.background = "white";	                			
	                		}
	                		if(qty<=0){	                		
	                			document.getElementsByName("qty")[index].style.background = "yellow";
	                		}
	                		
	                		submitButtonStatus(e);      
	                
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
	            	console.log("Error block: "+errorThrown)
	            }
        });
		
	}
	
	function submitButtonStatus(e){
		let col = document.getElementsByName("qty");
		let count= 0;
		for(let x=0; x<col.length; x++){
			if(col[x].style.background=='red' || col[x].style.background=='yellow'){
				count++;
			}
		}
		if(count !=0){
			$('.btn-status').prop('disabled', true);
		}
		else{
			$('.btn-status').prop('disabled', false);
		}
	}
	
	
	
	
	/* function called when any change is done in any price or quantity field */
	function cal(e){
		
		/* finding row index */
		let index = $(e).parent().parent().index();
		let amt = parseInt(document.getElementsByName("qty")[index].value)*parseInt(document.getElementsByName("price")[index].value);
		document.getElementsByName("amount")[index].value = amt;
		
		/* Checking whether enterd quantity is available  */
		getMasterSkuQuantity(e);
		/* Setting total  */
		setTotal();
		/* calculatin CGST on every change  */
		calGST();
	}
	
	
	
	
	/* Get Total */
	function setTotal(){
		let sum=0;
		let amounts = document.getElementsByName("amount");
		for(let i=0; i<amounts.length; i++){
			sum = sum + +(amounts[i].value);
		}
		document.getElementById("fTotal").value=sum;
	}
	
	
	
	
	/* Calculating CGST */
	function calGST(){
		
		let fTotal = document.getElementById("fTotal").value;
		
		let fcgst =0;
		let fsgst =0;
		let figst =0;
		fcgst= document.getElementById("fcgst").value;
		fsgst= document.getElementById("fsgst").value;
		figst= document.getElementById("figst").value;
		let newCgst = (fcgst*fTotal)/100;
		let newfSgst = (fsgst*fTotal)/100;
		let newfIgst = (figst*fTotal)/100;
		
		document.getElementById("fgTotal").value = (+(fTotal)+ +(newCgst)+ +(newfSgst) + +(newfIgst));
	}
	
	
	/*Getting and adding courier charges to Grand Total*/
	function courierCharges(){
		//let x = document.getElementById("courierCharges").value;
		console.log('hello');
	}
	
	
	
	/* Setting description  */
	function setDesc(e){
		let index = $(e).parent().parent().index();
		let x = document.getElementsByName("masterSku")[index].value;
		
		/* making an ajax call to retreive data from database by sending a request to controller */
				$.ajax({
			            type: 'POST',
			            url: '/get-mSku-desc',
			            data: {'title':x},
			            success: function (data, textStatus ,jqXHR) {
			                //console.log("Success block: "+data)
			                document.getElementsByName("particulars")[index].value=data;
			                $('textarea').val(data);
			            },
			            error: function (jqXHR, textStatus, errorThrown) {
			            	console.log("Error block: "+errorThrown)
			            }
		        });
		
		/* Calling this function here to get the latest product select from the sku list */
		getMasterSkuQuantity(e);
	}
	
    function 
	
	
	
	
	/* function to print invoice */
	function goPrint(){
		window.print();
	}
	