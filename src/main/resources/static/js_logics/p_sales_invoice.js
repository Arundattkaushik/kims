$(document).ready(()=>{
	/*alert('hello ! Gorrillas')*/
	let url = window.location.href;
	let invoiceId = url.split("=")

	loadSaleInvoice(invoiceId[1]);
})

function getRowId(e){
	//$(e).parent().parent()
		let rowIndex = $(e).parent().parent().index();
		let selectedRow = $("#tbody tr")[rowIndex];
		let allCellsInSelectedRow = selectedRow.cells;
		let rowId = parseInt(allCellsInSelectedRow[0].innerHTML);
		return rowId
}


function loadSaleInvoice(e){	
	
	$.ajax({
		type:'Post',
		url:'/sale-invoice/invoice-by-id',
		data:{'id':e},
		success:(res)=>{
			
			
			//contact information
			document.getElementById('mobile').innerHTML = res.data.companyMobile;
			document.getElementById('additionalMobile').innerHTML = res.data.additionalContactNo;
			
			
			//company information section
			document.getElementById('company-name').innerHTML = res.data.companyName;
			document.getElementById('company-gstin').innerHTML = res.data.companyGstin;			
			document.getElementById('company-address').innerHTML = res.data.companyAddress;
			document.getElementById('udyam-reg-no').innerHTML = res.data.hudyamDl;
			
			
			//invoice number section
			document.getElementById('invoice-date').innerHTML = res.data.invDate;
			document.getElementById('invoice-number').innerHTML = res.data.invoiceNumber;
			document.getElementById('ewayno').innerHTML = res.data.ewayBillNo;
			document.getElementById('po-number').innerHTML = res.data.ponumber;
			document.getElementById('state-code').innerHTML = res.data.billToStateCode;
			
			
			//bill to section
			document.getElementById('bill_to_name').innerHTML = res.data.billToName;
			document.getElementById('bill_to_address').innerHTML = res.data.billToAddress;
			document.getElementById('bill_to_state').innerHTML = res.data.billToState;
			document.getElementById('bill_to_state_code').innerHTML = res.data.billToStateCode;
			document.getElementById('bill_to_gstin').innerHTML = res.data.billToGstInNo;
			
			
			
			//bill to section
			document.getElementById('ship_to_name').innerHTML = res.data.shippToName;
			document.getElementById('ship_to_address').innerHTML = res.data.shippToAddress;
			document.getElementById('ship_to_state').innerHTML = res.data.shippToState;
			document.getElementById('ship_to_state_code').innerHTML = res.data.shippToStateCode;
			document.getElementById('ship_to_gstin').innerHTML = res.data.shippToGstInNo;

						
			
			//GST % section
			document.getElementById('cgst_percent').innerHTML = res.data.fcgst;
			document.getElementById('sgst_percent').innerHTML = res.data.fsgst;
			document.getElementById('igst_percent').innerHTML = res.data.figst;
			
			
			
			//GST value section
			document.getElementById('cgst').innerHTML = res.data.fcgstAmt;
			document.getElementById('sgst').innerHTML = res.data.fsgstAmt;
			document.getElementById('igst').innerHTML = res.data.figstAmt;
			
			
			//courier charges 
			document.getElementById('courier_ch').innerHTML = res.data.courierCharges;
			document.getElementById('discount').innerHTML = '0';
			document.getElementById('gTotal').innerHTML = res.data.fgTotal;
			
			
			//price section
			document.getElementById('amountInWords').innerHTML = res.data.gtotalInWords;
			
			
			
			//bank details
			document.getElementById('bankName').innerHTML = res.data.bankName;
			document.getElementById('bankAcNumber').innerHTML = res.data.bankAccountNo;
			document.getElementById('branchIFSC').innerHTML = res.data.bankIFSC;
			
			
			//invoice footer
			document.getElementById('termsAndConditions').innerHTML = res.data.termsAndCondtions;
			document.getElementById('company_name').innerHTML = res.data.companyName;

			
			//table seciton
			document.getElementById('totalAmt').innerHTML=res.data.ftotal;

			
			//printing table 
			for(let i=0; i<res.data.masterSku.length; i++){
				
				let tbody = $('#table-body');
				tbody.append(`<tr>
								  <td>`+(i+1)+`</td>
								  <td>`+res.data.masterSku[i].mhsn+`</td>
								  <td>`+res.data.masterSku[i].mskuTitle+`</td>
								  <td>`+res.data.masterSku[i].mskuDesc+`</td>
								  <td>`+res.data.uom[i]+`</td>
								  <td>`+res.data.qty[i]+`</td>
								  <td>`+res.data.price[i]+`</td>
								  <td>`+res.data.amount[i]+`</td>
							</tr>`);									
									
			}			
			
		},
		error:(errorThrown)=>{
			alert('Ah! Something went worng!')
			alert(errorThrown)
		}		
		
	});
}