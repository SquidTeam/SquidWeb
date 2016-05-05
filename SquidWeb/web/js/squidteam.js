$(function(){
   $(document).ready(function(){
	   
	   $.ajax({
	        url:'squidteam/connexion/verification',
	        type:'POST',
	        data:'',
	        success:function(response){
	        	$("#reussie").css("display","block");
	        	$("#inscription").css("display", "none");
	        },
	        error:function(error){
	        
	        }
	    });
	   
	   $("#submitInscription").click(function(){
		   var nom = $("#inputNom").val();
		   var prenom = $("#inputPrenom").val(); 
		   var email = $("#inputEmail").val();
		   var pseudo = $("#inputUtilisateur").val();
		   var pwd = $("#inputMdp").val();
		   var confirmation = $("#inputConfirmation").val();
		   console.log("inscription");
		   $.ajax({
			   url:'squidteam/inscription/signup',
			   type:'POST',
			   data:'pseudo='+pseudo+'&pwd='+pwd+"&email="+email+"&nom="+ nom +"&prenom="+prenom+"&confirmation="+confirmation,
			   success:function(response){
			       $("#reussie").css("display","block");
			       $("#inscription").css("display", "none");
			   },
			   error:function(e){
				   console.log(e);
			   }
		   });
	   });
   });
});   

