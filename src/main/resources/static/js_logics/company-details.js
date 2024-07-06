$(document).ready(() => {
	$.ajax({
		type: 'Get',
		url: '/get-company-details',
		success: function(data) {

			if (data != '') {
				document.getElementById('companyName').value = data.companyName;
				document.getElementById('companyOwner').value = data.companyOwner;
				document.getElementById('companyEmail').value = data.companyEmail;
				document.getElementById('companyContactNo').value = data.companyContactNo;
				document.getElementById('country').value = data.country;
				document.getElementById('state').value = data.state;
				document.getElementById('city').value = data.city;
				document.getElementById('zipCode').value = data.zipCode;
				document.getElementById('gstin').value = data.gstin;
				document.getElementById('companyMailingAddress').value = data.companyMailingAddress;
				document.getElementById('bankName').value = data.bankName;
				document.getElementById('bankAccountNumber').value = data.bankAccountNumber;
				document.getElementById('bankIfscCode').value = data.bankIfscCode;
				document.getElementById('account_ty').innerHTML = data.accountType;
				document.getElementById('termsAndConditions').innerHTML = data.termsAndConditions;
				document.getElementById('additionalContactNo').value = data.additionalContactNo;
				
				if(data.accountType=='saving'){
					document.getElementById('saving').checked=true;
				}
				if(data.accountType=='current'){
					document.getElementById('current').checked=true;			
				}
				if(data.accountType=='salary'){
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