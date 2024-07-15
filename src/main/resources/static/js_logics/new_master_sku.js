$(document).ready(function(){	
	
	// Function to load List of Raw Sku's
	loadRowSkuList();		
});


function createMaster(){		
		$.ajax({
				type: 'Post',
				url:'/msku/create',	
				async: false,
				caches: false,					
				data: $('#new_master_form').serialize(),
				success:()=>{
					alert("Created Successfully!")
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
		url:'/rawsku/list',
		success:function(data, errorThrown){
				if(data == null || data == ''){
					console.log(errorThrown)
				}
				else{				
				for(let i=0; i<data.data.length; i++){
					
					$('#rSkuIds').append(
						'<option value="' + data.data[i].id + '">' + data.data[i].name + "</option>"
					);
				}
			}
		},
		error: function (errorThrown){
			console.log(errorThrown);
		}
	})
}




	
		
		

	
	










