var list;

$(document).ready(function() {

	$('#get_user').click(function() {
		var size = new Number($('#number').val());
		$.get('https://randomuser.me/api/?results=' + size, function(res) {
			$('#result').html('');
			console.log(res);
			$.each(res.results, function(index, value) {

				$.get('/WebApp/content/content.html', function(template) {
				var t=	template.replace('user_name',value.name.first +' '+value.name.last);
				t=t.replace('img_url',value.picture.medium);
				$('#result').append(t);
				});
				
				
			});

		}).done(function() {
		}).fail(function() {
		}).always(function() {
		});

	});
});
