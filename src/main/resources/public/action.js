let btn = document.getElementById('buttonB');
btn.addEventListener('click',() =>{
    let numbers = "kk";
    var reqFoodId = document.getElementById('fname').value;
	axios.post('/base', reqFoodId)
        .then(res => {
            console.log(res);
			alert(res.data);
        })
        .catch( error =>{
            console.log("Intentalo de nuevo");
        })
	
});
