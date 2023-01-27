//import print from 'print-js'
function openPage(url) {

	if(url.indexOf("?") == -1) {
		url = url;
	}
	var encodedUrl = encodeURI(url);
	console.log(encodedUrl);
	$(".containerM").load(encodedUrl);
	$("body").scrollTop(0);
	history.pushState(null, null, url);
}

function openPage2(url) {

	if(url.indexOf("?") == -1) {
		url = url;
	}
	var encodedUrl = encodeURI(url);
	console.log(encodedUrl);
	$("body").load(encodedUrl);
	$("body").scrollTop(0);
	history.pushState(null, null, url);
}

var item1 = 0; var item2 = 0; var item3 = 0;
var buttons = document.getElementsByClassName('buttonMenu');
var buttonsAdmin = document.getElementsByClassName('buttonMenuAdmin');
function StyleChanger1Admin() {
    if (item2 == 1 || item3 == 1) {
        buttonsAdmin[1].style.backgroundColor = "#F5F0BB";
        buttonsAdmin[2].style.backgroundColor = "#F5F0BB";
        document.getElementById('aSelected2').style.color = "black";
        document.getElementById('aSelected3').style.color = "black";
    }
    buttonsAdmin[0].style.backgroundColor = "#90C8AC";
    document.getElementById('aSelected1').style.color = "#FEFBF6";
    item1 = 1;
    //openPage("NvFacture.html");
}
function StyleChanger1() {
    if (item2 == 1 || item3 == 1) {
        buttons[1].style.backgroundColor = "#8ab6d6";
        buttons[2].style.backgroundColor = "#8ab6d6";
        document.getElementById('aSelected2').style.color = "black";
        document.getElementById('aSelected3').style.color = "black";
    }
    buttons[0].style.backgroundColor = "#0061a8";
    document.getElementById('aSelected1').style.color = "gainsboro";
    item1 = 1;
    //openPage("NvFacture.html");
}
function StyleChanger2Admin() {
    if (item1 == 1 || item3 == 1) {
        buttonsAdmin[0].style.backgroundColor = "#F5F0BB";
        buttonsAdmin[2].style.backgroundColor = "#F5F0BB";
        document.getElementById('aSelected1').style.color = "black";
        document.getElementById('aSelected3').style.color = "black";
    }
    buttonsAdmin[1].style.backgroundColor = "#90C8AC";
    document.getElementById('aSelected2').style.color = "#FEFBF6";
    item2 = 1;
    //openPage("test.html");
}
function StyleChanger2() {
    if (item1 == 1 || item3 == 1) {
        buttons[0].style.backgroundColor = "#8ab6d6";
        buttons[2].style.backgroundColor = "#8ab6d6";
        document.getElementById('aSelected1').style.color = "black";
        document.getElementById('aSelected3').style.color = "black";
    }
    buttons[1].style.backgroundColor = "#0061a8";
    document.getElementById('aSelected2').style.color = "gainsboro";
    item2 = 1;
    //openPage("test.html");
}
function StyleChanger3Admin() {
        if (item1 == 1 || item2 == 1) {
            buttonsAdmin[0].style.backgroundColor = "#F5F0BB";
            buttonsAdmin[1].style.backgroundColor = "#F5F0BB";
            document.getElementById('aSelected1').style.color = "black";
            document.getElementById('aSelected2').style.color = "black";
        }
        buttonsAdmin[2].style.backgroundColor = "#90C8AC";
        document.getElementById('aSelected3').style.color = "#FEFBF6";
        item3 = 1;
}
function StyleChanger3() {
        if (item1 == 1 || item2 == 1) {
            buttons[0].style.backgroundColor = "#8ab6d6";
            buttons[1].style.backgroundColor = "#8ab6d6";
            document.getElementById('aSelected1').style.color = "black";
            document.getElementById('aSelected2').style.color = "black";
        }
        buttons[2].style.backgroundColor = "#0061a8";
        document.getElementById('aSelected3').style.color = "gainsboro";
        item3 = 1;
}
function modify1Admin() {
    buttonsAdmin[0].style.backgroundColor = "#90C8AC";
    document.getElementById('aSelected1').style.color = "#FEFBF6";
    item1 = 1;
}
function modify1() {
    buttons[0].style.backgroundColor = "#0061a8";
    document.getElementById('aSelected1').style.color = "gainsboro";
    item1 = 1;
}
function modify2Admin() {
    buttonsAdmin[1].style.backgroundColor = "#90C8AC";
    document.getElementById('aSelected2').style.color = "#FEFBF6";
    item2 = 1;
}
function modify2() {
    buttons[1].style.backgroundColor = "#0061a8";
    document.getElementById('aSelected2').style.color = "gainsboro";
    item2 = 1;
}
function modify3Admin() {
    buttonsAdmin[2].style.backgroundColor = "#90C8AC";
    document.getElementById('aSelected3').style.color = "#FEFBF6";
    item3 = 1;
}
function modify3() {
    buttons[2].style.backgroundColor = "#0061a8";
    document.getElementById('aSelected3').style.color = "gainsboro";
    item3 = 1;
}

function logout() {
    swal({
        title: "Êtes-vous sûr?",
        text: "Voulez-vous vraiment vous déconnecter ?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((willLogout) => {
        if (willLogout) {
	        $.post("includes/functions/logout.php", function() {
		    location.reload();
	        })
        }
    });
}
function logout0() {

	var prompt = confirm("Voulez-vous vraiment vous déconnecter ?");
	if(prompt) {
		$.post("/admin/logout")
		.done(function() {
		});
	}

}

function deleteProduct0(productId) {

	var prompt = confirm("êtes-vous sûr de vouloir supprimer ce produit?");
	if(prompt) {
		$.get("product/delete/"+productId, {productId: productId})
		.done(function() {
		});
	}

}

function deleteUser0(userId) {

	var prompt = confirm("êtes-vous sûr de vouloir supprimer cet utilisateur?");
	if(prompt) {
		$.get("user/delete/"+userId, {userId: userId})
		.done(function() {
		});
	}

}
function deleteUser(userId){
    swal({
        title: "Êtes-vous sûr?",
        text: "Vous ne pourrez plus récupérer cet utilisateur!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
            $.get("user/delete/"+userId, {userId: userId})
			.done(function() {
			});
            swal("Votre utilisateur a été supprimé!", {
                icon: "success",
            });
        } else {
          swal("Votre utilisateur est sûr!");
        }
      });
}
let changeStatus = element => {
	swal({
        title: "Êtes-vous sûr de cette operation?",
        text: "Un changement de statut se produira!",
        icon: "info",
        buttons: true,
        dangerMode: true,
      })
      .then((willUpdate) => {
        if (willUpdate) {
            $.get("user_status/"+element.getAttribute('data-id'))
				.done(function() {
			});
        } else {
          swal("Ce statut est sûr!");
        }
      });
}

function updateProduct0(productId) {

	var prompt = confirm("êtes-vous sûr de vouloir modifier ce produit?");
	if(prompt) {
		//$.get("product/"+productId, {productId: productId})
		//.done(function() {
			
			$( ".containerM" ).load( "product/"+productId );
			
		//});
	}

}
function updateUser0(userId) {

	var prompt = confirm("êtes-vous sûr de vouloir modifier cet utilisateur?");
	if(prompt) {
		//$.get("product/"+productId, {productId: productId})
		//.done(function() {
			
			$( ".containerM" ).load( "user/"+userId );
			
		//});
	}

}
function updateUser(userId){
    swal({
        title: "Êtes-vous sûr?",
        text: "Un changement d'information sur l'utilisateur se produira!",
        icon: "info",
        buttons: true,
        dangerMode: true,
      })
      .then((willUpdate) => {
        if (willUpdate) {
            $( ".containerM" ).load( "user/"+userId );
        } else {
          swal("Votre produit est sûr!");
        }
      });
}

function deleteProduct(productId){
    swal({
        title: "Êtes-vous sûr?",
        text: "Vous ne pourrez plus récupérer ce produit!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((willDelete) => {
        if (willDelete) {
            $.get("product/delete/"+productId, {productId: productId})
			.	done(function() {
			});
            swal("Votre produit a été supprimé!", {
                icon: "success",
            });
        } else {
          swal("Votre produit est sûr!");
        }
      });
}

function updateProduct(productId){
    swal({
        title: "Êtes-vous sûr?",
        text: "Un changement d'information sur le produit se produira!",
        icon: "info",
        buttons: true,
        dangerMode: true,
      })
      .then((willUpdate) => {
        if (willUpdate) {
            $( ".containerM" ).load( "product/"+productId );
        } else {
          swal("Votre produit est sûr!");
        }
      });
}

function valideJob2(){
 	document.getElementsByClassName('shownone')[0].remove();
    document.getElementsByClassName('gridViewContainer')[0].style.display = "none";
    document.getElementById('video').style.display = "block";
    document.getElementsByClassName('vidclass')[0].style.display = "initial";
    document.getElementById('snap').style.display = "none";
    document.getElementById('valider').style.display = "initial";
                document.getElementsByClassName('snapbutton')[0].remove();
                document.getElementsByClassName('vidclass')[0].remove();
                
                document.getElementsByClassName('topSelectorSugg')[0].style.display = "flex";

                document.getElementsByClassName('SecondmodifierSugg')[0].style.display = "initial";
                document.getElementsByClassName('SecondmodifierSugg')[1].style.display = "initial";
                document.getElementsByClassName('SecondmodifierSugg')[2].style.display = "initial";

                document.getElementsByClassName('buttonMenu2Sugg')[0].style.display = "initial";
                document.getElementsByClassName('buttonMenu2Sugg')[1].style.display = "initial";
                document.getElementsByClassName('buttonMenu2Sugg')[2].style.display = "initial";
                
                document.getElementsByClassName('gridViewContainer')[0].style.display = "initial";
                document.getElementsByClassName('titleSuggest')[0].style.display = "flex";
                changeContent(1);
                document.getElementById('poids').innerHTML = "Poids ".concat(String(randPoix)," KG");
}

var randPoix = toFixed(Math.random()*3,3);
localStorage.setItem('poids',randPoix);

var num = 0;
function changeContent(nb){
    if(nb == 0){
        document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "cornsilk";
        document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "cornsilk";
        document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "cornsilk";
        $( ".gridViewContainer" ).load( "/fragments/remplaceAll" );
        num = 1;
    }else if(nb == 1){
        document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "cornsilk";
        document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "cornsilk";
        document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "cornsilk";
        $( ".gridViewContainer" ).load( "/fragments/selectPopularFacture" );
        num = 0;
    }
    else if(nb == 2){
        $( ".gridContainer" ).load( "/fragments/selectFruit" );
        document.getElementsByClassName("buttonMenu2")[0].style.backgroundColor = "whitesmoke";
        document.getElementsByClassName("buttonMenu2")[1].style.backgroundColor = "cornsilk";
        document.getElementsByClassName("buttonMenu2")[2].style.backgroundColor = "cornsilk";
    }
    else if(nb == 3){
        $( ".gridContainer" ).load( "/fragments/selectLegume" );
        document.getElementsByClassName("buttonMenu2")[1].style.backgroundColor = "whitesmoke";
        document.getElementsByClassName("buttonMenu2")[0].style.backgroundColor = "cornsilk";
        document.getElementsByClassName("buttonMenu2")[2].style.backgroundColor = "cornsilk";
    }
    else if(nb == 4){
        $( ".gridContainer" ).load( "/fragments/selectVrac" );
        document.getElementsByClassName("buttonMenu2")[2].style.backgroundColor = "whitesmoke";
        document.getElementsByClassName("buttonMenu2")[0].style.backgroundColor = "cornsilk";
        document.getElementsByClassName("buttonMenu2")[1].style.backgroundColor = "cornsilk";
    }
}

function changeContentSugg(nb){
    if(nb == 0){
        if(num == 1){
            $( ".gridViewContainer" ).load( "/fragments/remplaceAllFruit" );
            document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "whitesmoke";
            document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "cornsilk";
            document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "cornsilk";
        }else if(num == 0){
            $( ".gridViewContainer" ).load( "/fragments/remplacePopularFruit" );
            document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "whitesmoke";
            document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "cornsilk";
            document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "cornsilk";
        }
        
    }else if(nb == 1){
        if (num == 1) {
            $( ".gridViewContainer" ).load( "/fragments/remplaceAllLegume" );
            document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "whitesmoke";
            document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "cornsilk";
            document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "cornsilk";
        } else if(num == 0) {
            $( ".gridViewContainer" ).load( "/fragments/remplacePopularLegume" );
            document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "whitesmoke";
            document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "cornsilk";
            document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "cornsilk";
        }
        
    }
    else if(nb == 2){
        if (num == 1) {

            $( ".gridViewContainer" ).load( "/fragments/remplaceAllVrac" );
            document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "whitesmoke";
            document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "cornsilk";
            document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "cornsilk";
        
        } else if(num == 0){

            $( ".gridViewContainer" ).load( "/fragments/remplacePopularVrac" );
            document.getElementsByClassName("buttonMenu2Sugg")[2].style.backgroundColor = "whitesmoke";
            document.getElementsByClassName("buttonMenu2Sugg")[1].style.backgroundColor = "cornsilk";
            document.getElementsByClassName("buttonMenu2Sugg")[0].style.backgroundColor = "cornsilk";
        }
        
    }
}

function addComma(){
    const texte = String(document.getElementById('prix').value);
    document.getElementById('prix').value = texte.concat(".");
}

function toFixed(value, precision) {
    var power = Math.pow(10, precision || 0);
    return Math.round(value * power) / power;
}

function process(fID,pID) {
    localStorage.setItem('fID',fID);
    localStorage.setItem('pID',pID);
    console.log("pID = "+localStorage.getItem('pID'));
    console.log("fID = "+localStorage.getItem('fID'));
    getBarcode(fID);
}

function getBarcode(ID){
	var qrcode = new QRCode(document.querySelector("#heh"), {
        text: `${ID}`,
        width: 180, //128
        height: 180,
        colorDark : "#000000",
        colorLight : "#ffffff",
        correctLevel : QRCode.CorrectLevel.H
    });

    document.getElementById('heh').appendChild(qrcode._oDrawing._elImage);

    var someimage = document.getElementById('heh');
    var myimg = someimage.getElementsByTagName('img')[0];
    myimg.classList.add("image");
}

