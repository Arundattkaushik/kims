$(document).ready(() => {
	$.ajax({
		type: 'Get',
		url: '/company/detail',
		success: function(res) {
			
			if (res != '') {
				document.getElementById('companyName').value = res.data.companyName;
				document.getElementById('companyOwner').value = res.data.companyOwner;
				document.getElementById('companyEmail').value = res.data.companyEmail;
				document.getElementById('companyContactNo').value = res.data.companyContactNo;
				document.getElementById('country').value = res.data.country;
				document.getElementById('state').value = res.data.state;
				document.getElementById('city').value = res.data.city;
				document.getElementById('zipCode').value = res.data.zipCode;
				document.getElementById('gstin').value = res.data.gstin;
				document.getElementById('hudyamDl').value = res.data.hudyamDl;
				document.getElementById('companyMailingAddress').value = res.data.companyMailingAddress;
				document.getElementById('bankName').value = res.data.bankName;
				document.getElementById('bankAccountNumber').value = res.data.bankAccountNumber;
				document.getElementById('bankIfscCode').value = res.data.bankIfscCode;
				document.getElementById('account_ty').innerHTML = res.data.accountType;
				document.getElementById('termsAndConditions').innerHTML = res.data.termsAndConditions;
				document.getElementById('additionalContactNo').value = res.data.additionalContactNo;
				
				if(res.accountType=='saving'){
					document.getElementById('saving').checked=true;
				}
				if(res.accountType=='current'){
					document.getElementById('current').checked=true;			
				}
				if(res.accountType=='salary'){
					document.getElementById('salary').checked=true;			
				}

			}

		},
		error: function(errorThrown) {
			console.log("Error block run for => setBillToDetails() " + errorThrown)
		}
	});

	$('label').css('color', 'yellow');
	$('#address').css('color', 'green');
	$('input').prop('disabled', true);
	$('textarea').prop('disabled', true);
});


function enableEdit() {
	document.getElementById('edit_btn').style.display = 'none';
	document.getElementById('update_btn').style.display = 'block';
	document.getElementById('account_type_default').style.display = 'none';
	document.getElementById('account_type').style.display = 'block';
	$('input').prop('disabled', false);
	$('textarea').prop('disabled', false);
}