$(document).ready(()=>{
	$('#address').css('color','red');
	//alert('hello gorillas!')
	
})


function creatParty(){
	let form = $('#party_form')
	let formData = form.serialize();
	
	$.ajax({
		type:'Post',
		url:'/party/create',
		data:formData,
		success:(res)=>{
			//alert(res.status)
			if(res.status=='OK'){
				alert('Party created successfully!')
				window.location.href='/party-list';
			}
			else{
				alert('Error while creating party!')
				window.location.href='/new-party';
			}			
		},
		error:()=>{
				alert('Oops! Something went wrong')
				window.location.href='/error';
		}
	})
}