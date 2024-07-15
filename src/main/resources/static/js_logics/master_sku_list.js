$(document).ready(function(){
	
	loadTableData();
})

function loadTableData(){
	$('#data-table').DataTable({
		
					paging: true,
				    scrollCollapse: true,
				    scrollY: '415px',
		
				ajax: {
									type: 'Get',
									url: '/raw-master/list',								
									/*success:(data)=>{
										
											    console.log(`Key: ${key}, Value: ${value}`);
											    console.log(data.data[0].rawSku[0].name);
											}
										
									}	*/			
					},
						columns:[
								{data:"mid", class:"text-start"},
								{data:"mtitle",
								render: function (data) {								 
								               return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="showModel(this)")>'+ data+'</a>';								                
								        }						
									},
								{data:"ordRawSkus[].name", 
								render: (data)=>{
									return '<p style="color: blue;">'+ data+'</p>';
								}},
								{data:"mhsn"},
								{data:"mdesc"},								
								{data:()=>{
									return `<a onclick='deleteRow(this)' style="cursor: pointer" hidden=true><img alt="delete-btn" src="img/delete.png" class="icon-s"></a>`
								}, class:"text-center"
							}
								
							],
							order: {
							       data:"invoiceNumber",
							        dir: 'asc'
							    }

				
	});
	
}




function getRowId(e){
	//$(e).parent().parent()
		let rowIndex = $(e).parent().parent().index();
		let selectedRow = $("#tbody tr")[rowIndex];
		let allCellsInSelectedRow = selectedRow.cells;
		let rowId = parseInt(allCellsInSelectedRow[0].innerHTML);
		return rowId
}


function showModel(e){
	document.getElementById('data-table').style.opacity=0.2;
	document.getElementById('pop-box').classList.add('popup-show');
	
	let mId = getRowId(e);
	$.ajax({
		type:'Post',
		url:'/raw-master/get',
		data:{'id':mId},
		success:(data)=>{
			document.getElementById('masterSkuTitle').value=data.data[0].mtitle;
			document.getElementById('hsn').value=data.data[0].mhsn;
			document.getElementById('masterSkuDescription').innerHTML=data.data[0].mdesc;			
		

			let tbody = $('#tbody2');
			for(let i=0; i<data.data[0].ordRawSkus.length; i++){
				
				tbody.append(`
				<tr>
					<td>
						<input readonly class="form-control" value="`+data.data[0].ordRawSkus[i].name+`"/>	
					</td>
					<td>
						<input readonly type="number" class="form-control" id="rawskuquantity" name="rawskuquantity" value="`+data.data[0].ordRawSkus[i].ordQty+`">
					</td>
				</tr>				
				`);
			}
			
		},
		error:(errorThrown)=>{
			alert('Something went wrong!'+errorThrown)
		}
	})
}


function closeModel(){
	document.getElementById('pop-box').classList.remove('popup-show')
	document.getElementById('data-table').style.opacity=1;
}


function updateMaster(){
	//let x = getRowId(e);
	alert('x')
	
	
}

function deleteRow(e){
	let rId = getRowId(e);
	let delConfirm = confirm('Are you sure want to delete?');
	if(delConfirm==true){
		$.ajax({
				type:'post',
				url:'/msku/delete',
				data:{"id":rId},
				success:function(){
					location.reload();
				}
			})			
	}	
}

