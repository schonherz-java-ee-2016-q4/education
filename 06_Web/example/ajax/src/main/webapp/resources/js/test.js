function add() {
	$('#c').addClass('a');
}

var list ;

$(document).ready(function() {
	$('#b').addClass('a');
	$('#buttonA').click(function() {
		add();
		$('#buttonA').prop('disabled', true);
		$('#c').html('Loading...');
		$.get('/WebApp/resources/json/test.json',function(res){
			console.log(res);
			list=res;
			$('#select').html('');
			$.each(list, function(index, value) {
				$('#select').append('<option value="'+value.value +'">' + value.name + '</option>');

			});
			
		}).done(function() {
		    alert( "second success" );
		  })
		  .fail(function() {
		    alert( "error" );
		  })
		  .always(function() {
			  $('#buttonA').prop('disabled', false);
				$('#c').html('');
		  });
		
		

	});
});

 