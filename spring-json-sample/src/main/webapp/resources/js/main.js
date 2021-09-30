$(function() {
	$('#getBtn').on('click', function() {
		var param = {
				id : $('#id').val(),
				pwd : $('#pwd').val()
			};
		$.ajax({
			method : 'get',
			url : '/ajax/testGet',
			dataType : 'json',
			data : param,
			success : function(result) {
				$('#resultLbl').text(JSON.stringify(result));
			},
			fail : function(result) {
				console.log(result);
			}
		});
	});

	$('#postStrBtn').on('click', function() {
		var param = {
				id : $('#id').val(),
				pwd : $('#pwd').val()
			};
		$.ajax({
			method : 'POST',
			url : '/ajax/testPostStr',
			contentType: 'application/json',
			dataType : 'json',
			data : JSON.stringify(param),
			success : function(result) {
				$('#resultLbl').text(JSON.stringify(result));
			},
			fail : function(result) {
				console.log(result);
			}
		});
	});

	$('#postMapBtn').on('click', function() {
		var param = {
				id : $('#id').val(),
				pwd : $('#pwd').val()
			};
		$.ajax({
			method : 'POST',
			url : '/ajax/testPostMap',
			contentType: 'application/json',
			dataType : 'json',
			data : JSON.stringify(param),
			success : function(result) {
				$('#resultLbl').text(JSON.stringify(result));
			},
			fail : function(result) {
				console.log(result);
			}
		});
	});
	
	$('#postVOBtn').on('click', function() {
		var param = {
				id : $('#id').val(),
				pwd : $('#pwd').val()
			};
		$.ajax({
			method : 'POST',
			url : '/ajax/testPostVO',
			contentType: 'application/json',
			dataType : 'json',
			data : JSON.stringify(param),
			success : function(result) {
				$('#resultLbl').text(JSON.stringify(result));
			},
			fail : function(result) {
				console.log(result);
			}
		});
	});
});