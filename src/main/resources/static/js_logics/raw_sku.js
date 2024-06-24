$(document).ready(()=>{
	dataTable();
})

function dataTable(){
	$('#data-table').dataTable(); 

	$.ajax({
	       type: 'GET',
	       url: '/get-raw-sku-list'
		 });
}





/*$(document).ready(()=>{
	
	 $('#data-table').dataTable(); 
	
	$.ajax({
        type: 'GET',
        url: '/get-raw-sku-list'
        
        success: function (data, textStatus ,jqXHR) {
             console.log("Success block: "+data);  
             data.forEach(function(e){
            	tableDate=data;
    			console.log(e)
            }) 
            
             $('#data-table').dataTable({
            	data: data,
            	columns:[
            		{'data': 'id'},
            		{'data': 'name'},
            		{'data': 'party_list'},
            		{'data': 'price_per_unit'},
            		{'data': 'quantity'},
            		{'data': 'description'}
            	]
            	
            }) 
            
            
            
            
            
			 make a dataTable column clikable 
             $('#data-table').on('click', 'tbody td', function() {

			  //get textContent of the TD
			  console.log('TD cell textContent : ', this.textContent)
			
			}); 
            
            
            
            
             code to append list options dynamically with jQuery 
             $.each(data, function (i, item) {
            	$("#party_list").append($('<option>', { 
                    value: item,
                    text : item 
                }));
            }); 
            
            
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
        	console.log("Error block: "+errorThrown)
        }
});
})*/
