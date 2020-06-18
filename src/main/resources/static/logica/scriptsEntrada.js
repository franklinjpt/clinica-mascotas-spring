$(function(){

	$("#form-login input[type=submit]").on('click',function(ev){
		ev.preventDefault();
		var url = 'http://localhost:8080/login';
		var username = $('#login').val();
		var pwd = $('#password').val();
		login(url,username,pwd);
	});
});

var login = function(url, username, passwd){
	$.ajax({
		url:url,
		method:'POST',
		headers: {
			Accepts: "application/json"
		},
		contentType:'application/json',
		data:JSON.stringify({"username":username,"password":passwd}),
		success:function(data, textStatus, jqXHR){
			token = jqXHR.getResponseHeader("Authorization");
			console.log('console: login success;: '+token);
			localStorage.setItem('token',token);

			//locar la lógica después de logarse
            location.href = "/index";

		}
	});
};