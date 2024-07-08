$(document).ready(function(){	
	
	// Function to load List of Raw Sku's
	loadRowSkuList();		
});


function submitForm(){		
		$.ajax({
				type: 'Post',
				url:'/create-master',	
				async: false,
				caches: false,					
				data: $('#new_master_form').serialize(),
				success:()=>{
							window.location.href='/master-sku-list';	
							return false;
												
						},
				error:(errorThrown)=>{
							alert(errorThrown)
							window.location.href='/error';
						}
			});
			
}


function redirectTo(){
	alert('Created Successfully!');	
}


function addRow(){
		var v = $('#trow').clone().appendTo('#tbody');
		$(v).find("input").val('');
	}
	
function removeRow(e){
			$(e).parent().parent().remove();
    	}
		
function loadRowSkuList(){
	/*console.log('hello')*/
	$.ajax({
		type:'Get',
		url:'/get-raw-sku-list',
		success:function(data, errorThrown){
				if(data == null || data == ''){
					console.log(errorThrown)
				}
				else{				
				for(let i=0; i<data.data.length; i++){
					
					$('#rawSku').append(
						'<option value="' + data.data[i].name + '">' + data.data[i].name + "</option>"
					);
				}
			}
		},
		error: function (errorThrown){
			console.log(errorThrown);
		}
	})
}




	
		
		

	
	










