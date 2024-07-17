$(document).ready(function() {
	dataTable();
})

function dataTable() {
	$('#table-data').dataTable({
		paging: true,
		scrollCollapse: true,
		autoFill: true,							
		scrollY: '385px',			
		
		ajax: {
							type: 'Get',
							url: '/sales/list',
							dataSrc: 'data'				
			},
			
				columns:[
						{data:"invoiceNumber", class:"text-center", 
						render: function (data) {								 
							return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="viewSInvoice(this)">'+ data+'</a>';								                
						}},
						{data:(data)=>{
								return data.billToName==''?data.select_billTo_party:data.billToName;
							}							
						},
						{data:"hsn"},
						{data:"masterSku"},
						{data:"qty"},
						{data:"price"},
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

