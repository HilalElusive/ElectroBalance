const observer = new MutationObserver(()=>{
   setTimeout(() => {
	let url = document.getElementById('heh').getElementsByTagName('img')[0].src;
	let fid = localStorage.getItem('fID');
	let pid = localStorage.getItem('pID');
	let poids = localStorage.getItem('poids');
	
	const data = { url: url, poidNet: parseFloat(poids), idProd: pid, idFact: fid };
	console.log(url);
	fetch('/export/', {
  		method: 'POST',
  		headers: {
    	'Content-Type': 'application/json',
  	},
  		body: JSON.stringify(data),
	})
	.then((data) => {
  		//console.log('Success:', data);
  		openPage2("/facturation");
  		//document.getElementsByClassName('superpose')[0].style.display = "none";
	})
	.catch((error) => {
  		console.error('Error:', error);
	});
	
	
	localStorage.clear();
	
   }, 100);
    
})
observer.observe(document.querySelector("#heh"),{
    childList : true
})