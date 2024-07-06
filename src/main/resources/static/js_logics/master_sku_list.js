$(document).ready(function(){
	loadTableData();
})

function loadTableData(){
	$('#data-table').DataTable({
		
					paging: true,
				    scrollCollapse: true,
				    scrollY: '410px',
		
				ajax: {
									type: 'Get',
									url: '/msku-list',
									dataSrc: 'data'				
					},
						columns:[
								{data:"id", class:"text-start"},
								{data:"masterSkuTitle",
								render: function (data) {								 
								               return '<a style="text-decoration:none; color: blue; cursor: pointer;" onclick="updateRaw(this)")>'+ data+'</a>';								                
								        }						
									},
								{data:"hsn"},
								{data:"masterSkuDescription"},
								{data:"masterSkuPrice", class:"text-center"},								
								{data:()=>{
									return `<a onclick='deleteRow(this)' style="cursor: pointer"><img alt="delete-btn" src="img/delete.png" class="icon-s"></a>`
								}, class:"text-center"
							}
								
							],
							order: {
							       data:"invoiceNumber",
							        dir: 'asc'
							    }

				
			}			
			
	);
	
}


function getRowId(e){
	//$(e).parent().parent()
		let rowIndex = $(e).parent().parent().index();
		let selectedRow = $("#tbody tr")[rowIndex];
		let allCellsInSelectedRow = selectedRow.cells;
		let rowId = parseInt(allCellsInSelectedRow[0].innerHTML);
		return rowId
}

function getValuesOfAllCellsOfARow(e){
	let rowIndex = $(e).parent().parent().index();
	let selectedRow = $("#tbody tr")[rowIndex];
	let allCellsInSelectedRow = selectedRow.cells;
	
	const tdValue = [];
	for(let i=0; i< allCellsInSelectedRow.length; i++){
		tdValue[i] = allCellsInSelectedRow[i].innerHTML;
	}
	
	return tdValue;
}


function updateRaw(e){
	let x = getRowId(e);
	const rowArr = getValuesOfAllCellsOfARow(e);
	
	let mSkuTitle = $(rowArr[1])[0].innerHTML;
	let hsn = rowArr[2];
	let desc = rowArr[3];
	let amt = rowArr[4];
	
	
}

function deleteRowPerformed(e){
	$.ajax({
		type:'post',
		url:'/delete-master',
		data:{"id":getRowId(e)},
		success:function(){
			closePopup();
			location.reload();
		}
	})
}

function deleteRow(){
		document.getElementById('pop-box').classList.add('popup-show');		
}

function closePopup(){
	document.getElementById('pop-box').classList.remove('popup-show');
}

