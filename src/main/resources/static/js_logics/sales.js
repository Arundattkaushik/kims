$(document).ready(function() {
	dataTable();
})

function dataTable() {
	$('#table-data').dataTable({
		ajax: {
							type: 'Get',
							url: '/get-sales',
							dataSrc: 'data'				
			},
				columns:[
						{data:"invoiceNumber", class:"text-center"},
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

