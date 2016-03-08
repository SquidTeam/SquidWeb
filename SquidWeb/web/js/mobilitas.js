$(function(){
   
   $(document).ready(function(){
    $.ajax({
    url:'mobilitas/start',
    type:'POST',
    data:'',
    success:function(response){
         var json = response;
         login = response["login"];
         var  email = response["email"];
         var  nom = response["name"].toUpperCase();
         var prenom = response["prenom"];
        var profile = ""+nom+" "+prenom ;
        console.log(profile);
        $(".current-user").css("display","inline");
        $( "#rowConnexionInscription" ).css("display","none");
        $("#rowAccueil").css("display","inline");
        $("#username").html(profile);
        $("#email").html(email);
    },
    error:function(e){
        console.log("here");
        $("#rowConnexionInscription" ).css("display","inline");
        $("#rowAccueil").css("display","none");
        $(".current-user").css("display","none");
    }
    }) 
   
   });
    
    
    $("#connexionSubmit").click(function(){
        var login = $("#loginInputC").val();
        var pwd = $("#passwordInputC").val();
        
        $.ajax({
            url:'mobilitas/login',
            type:'POST',
            data:'login='+login+'&pwd='+pwd+'',
            success:function(response){
                    console.log(response);
                    login = response["login"]; 
                    var  email = response["email"];
                    var  nom = response["name"].toUpperCase();
                    var prenom = response["prenom"];
                    var profile = ""+nom+" "+prenom ;
                        console.log(profile);

                     $(".current-user").css("display","inline");
                    $( "#rowConnexionInscription" ).css("display","none");
                    $("#rowAccueil").css("display","inline");
                    $("#username").html(profile);
                    $("#email").html(email);
            
                    
            },
            error:function(e){
                $("#messageCo").val("Mauvais Login ou Mdp");
                $("#rowConnexionInscription" ).css("display","inline");
                $("#rowAccueil").css("display","none");
                 $(".current-user").css("display","none");
            }
        });
        
        
    });
    
    
    $("#InscriptionSubmit").click(function(){
      var login = $("#loginInputI").val();
      var pwd = $("#passwordInputI").val();
      var email = $("#emailI").val();
      var prenom = $("#prenomI").val();
      var nom = $("#nomI").val();
        
     
    
      console.log("#emailI");
      if ( isAlphaOrParen(nom) && isAlphaOrParen(prenom)){
      $.ajax({
            url:'mobilitas/signup',
            type:'POST',
            data:'login='+login+'&pwd='+pwd+"&email="+email+"&nom="+ nom +"&prenom="+prenom+"",
            success:function(response){
                   var  json = response;
                   login = response["login"];
                   var  email = response["email"];
                    var  nom = response["name"].toUpperCase();
                    var prenom = response["prenom"];
                    var profile = ""+nom+" "+prenom ;
                    $(".current-user").css("display","inline");
                    $( "#rowConnexionInscription" ).css("display","none");
                    $("#rowAccueil").css("display","inline");
                    $("#username").html(profile);
                    $("#email").html(email);
            },
            error:function(e){
                 $("#rowConnexionInscription" ).css("display","inline");
                 $("#rowAccueil").css("display","none");
                 $(".current-user").css("display","none");
            }
        });
      }
       else{
         $("#messageI").html(" Nom et Prenom : Uniquement des lettres");
         $("#messageI").css("color","red");
       }
    })
    
    
      function isAlphaOrParen(str) {
    return /^[a-zA-Z]+$/.test(str);
      }
    
    
    
    
    
    
    
    
    
});