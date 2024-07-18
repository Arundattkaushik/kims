$(document).ready(function() {
	dataTable();

})

function dataTable() {
	
	$('#table-data').dataTable({
		
		fixedColumns: true,
		paging: true,
		scrollCollapse: true,
		autoFill: true,							
		scrollY: '385px',			
		
		ajax: {
							type: 'Get',
							url: '/sales/list',
							dataSrc: 'data'				
			},
			columnDefs: [
			      { "width": "140px", "targets": 0 }, // Invoice No.
			      { "width": "180px", "targets": 1 }, // Party			      
			      { "width": "95px", "targets": 2 }, // HSN			      
			      { "width": "96px", "targets": 4 }, // Price			      
			   /*   { "width": "96px", "targets": 5 }, // Price*/			      
			      { "width": "30px", "targets": 6 }, // Sales Amount
			     /* { "width": "120px", "targets": 10 } // Total Amount*/
				  /*{ "width": "30px", "targets": [2, 3, 4, 5, 7, 8, 9] },*/ // HSN, SKU, Quantity, Price, CGST, SGST, IGST
			    ],
			
				columns:[
						{data:"invoiceNumber", class:"text-center", 
						render: function (data) {								 
							return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="viewSInvoice(this)">'+ data+'</a>';								                
						}},
						{data:(data)=>{
								return data.billToName==''?data.select_billTo_party:data.billToName;
							},							
						},
						{data:"hsn"},
						{data:"masterSku"},
						{data:"qty"},
						/*{data:"price"},*/
						{data:"ftotal"},
						{data:"fcgst"},
						{data:"fsgst"},
						{data:"figst"},
						{data:"fgTotal"}
						
					],
					order: {
					       data:"invoiceNumber",
					        dir: 'asc'
					    }

		
	})

}

//view-sinvoice
function viewSInvoice(e){
	//Getting the Id of clicked row
	let invoiceId = $(e).text();
	
	window.location.href='/view-sinvoice?id='+invoiceId;

}

