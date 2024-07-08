$(document).ready(()=>{
	//Loading party list
	loadPartyList();
		
})

function createRawSku(){
	let form = $('#new_raw');
	let formData = form.serialize();
	alert(formData)
	
	$.ajax({
		type:'Post',
		url:'/create-row-sku',
		data:formData,
		success:()=>{
			window.location.href='raw-sku-list';
		}
	})
	
}


function loadPartyList(){
	$.ajax({
	            type: 'GET',
	            url: '/parties-list',
	            
	            success: function (data, textStatus ,jqXHR) {
	                /* console.log("Success block: "+data); */
	                
					if(data == null || data == ''){
						console.log(errorThrown)
					}
					else{				
					for(let i=0; i<data.length; i++){
						
						$('#party_list').append(
							'<option value="' + data[i].partyName + '">' + data[i].partyName + "</option>"
										);
					}
				}
	                
	           },
	           error: function (jqXHR, textStatus, errorThrown) {
	           		alert("Something went wrong! "+errorThrown)
	           }
	    });
}