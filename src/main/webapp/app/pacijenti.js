Vue.component("pacijenti", {
	data: function () {
	    return {
	    	 input: {	    		 
                 ime: "",
                 prezime: "",
                 brojOsiguranika: ""
             		},
	    	tipovi:[],
	    	pacijent:[],
	    	karton:[],
	    	id:null,
	    	izmena:"",
	    	klinika_id:null,
	    	lekar_username:null,
	    	pregledi:[],
	    }
	},
	template: ` 
<div class="oneoption">
<div>
	<div class="jumbotron" style="width:105%;">
	  <h2>Pacijenti</h2>
	  <p>Pretraga i filtriranje.</p> 
	</div>
   <table align="left" class="table">
   		<tr>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput1" placeholder="Ime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput2" placeholder="Prezime"></th>
		   <th><input type="text" style="margin-left:10px;margin-bottom:10px;" class="fotrol" id="myInput3" placeholder="Broj osiguranika"></th>
		   <th><input class="btn btn-success"  type='button' value='Pretrazi'  v-on:click="fjaPretrage()"/></th>
		   <th><input class="btn btn-success"  type='button' value='Prikazi sve'  v-on:click="prikaziSve()"/></th>
		</tr>
		<tr>
		   <th>Ime</th>
		   <th>Prezime</th>
		   <th>Broj osiguranika</th>
		   <th></th>
		   <th>Profil</th>
		</tr>
		<tr v-for="tp in tipovi" v-if="tp.kc_id==klinika_id" class="filterDiv ">
			<td class="myclass">{{tp.ime}}</td>
			<td class="myclass">{{tp.prezime}}</td>
			<td class="myclass">{{tp.brojOsiguranika}}</td>
			<td></td>
			<td><input class="btn btn-success" style="margin-top:10px;" type='button' value='Pacijent'  v-on:click="prikaziPacijenta(tp,tp.karton)"/></td>
		</tr>
   </table>
   <div id="modaldark" style="overflow-y:scroll;">
   <div id="myForm">
   <div id="lkarton">
    <form>
		<h2 class="text-center">Profil pacijenta</h2>       
		<div class="lform-group">
			<label for="ime">Email: </label>
   			<input type="text" id = "username" class="form-control" v-model="pacijent.email"  disabled>
		</div>
		<div class="lform-group">
			<label for="prezime">Ime: </label>
			<input type="text" id = "prezime" class="form-control" v-model="pacijent.ime" disabled>
    	</div>
    	<div class="lform-group">
			<label for="pol">Prezime: </label>
   			<input type="text" id = "pol" class="form-control" v-model="pacijent.prezime" disabled>
		</div>
		<div class="lform-group">
			<label for="rodj">Adresa: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="pacijent.adresa" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Grad: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="pacijent.grad" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Drzava: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="pacijent.drzava" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Jedinstveni br. osiguranika: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="pacijent.brojOsiguranika" disabled>
		</div>
     </form>
     <form id="zkarton">
		<h2 class="text-center">Zdravstveni karton</h2>       
		<div class="lform-group">
			<label for="ime">Ime: </label>
   			<input type="text" id = "username" class="form-control" v-model="karton.ime"  disabled>
		</div>
		<div class="lform-group">
			<label for="prezime">Prezime: </label>
			<input type="text" id = "prezime" class="form-control" v-model="karton.prezime" disabled>
    	</div>
    	<div class="lform-group">
			<label for="pol">Pol: </label>
   			<input type="text" id = "pol" class="form-control" v-model="karton.pol" disabled>
		</div>
		<div class="lform-group">
			<label for="rodj">Datum rodjenja: </label>
    		<input type="text" id = "rodj" class="form-control"  v-model="karton.datumRodjenja" disabled>
		</div>
		<div class="lform-group">
			<label for="grupa">Krvna grupa: </label>
    		<input type="text"  id = "grupa" class="form-control" v-model="karton.krvnaGrupa" disabled>
		</div>
     </form>
     <div class="buttons">
		<button type="button" id = "pKartona" style="display:none;" class="btn maal" v-on:click="prikaziKarton()">Prikazi karton</button>
		<button type="button" id = "zPregled" style="display:none;" class="btn maal" v-on:click="">Zapocni pregled</button>
		<button type="button" class="btn zaal" v-on:click="otkazi()">Otkazi</button>
    </div>
	</div>
   </div>
   </div>
</div>
</div>		  
`
	, 
	methods : {
		prikaziPacijenta(pacijent,karton){
			axios
		    .get('rest/Pregled/svi')
		    .then(response => {
				this.pacijent=pacijent;
				this.karton=karton;
				document.getElementById("myForm").style.display = "block";
				document.getElementById("modaldark").style.display = "block";
				document.getElementById("modaldark").style.opacity="1";
		    	this.pregledi=response.data;
		    	var duzina=this.pregledi.length;
		    	for(var i =0;i<duzina;i++){
		    		var obj=this.pregledi[i];
		    		if(obj.lekar.username==this.lekar_username && obj.karton!=null && obj.dijagnoza==null){
		    			if(obj.karton.id==this.karton.id){
			    			this.karton=obj.karton;
			    			document.getElementById("zPregled").style.display = "block";
		    			}
		    		}
		    		if(obj.lekar.username==this.lekar_username && obj.karton!=null && obj.dijagnoza!=null){
		    			if(obj.karton.id==this.karton.id){
		    				this.karton=obj.karton;
		    				document.getElementById("pKartona").style.display = "block";
		    			}
		    		}
		    	}
		    });	
		},
		prikaziKarton(){
			document.getElementById("zkarton").style.display = "block";
		},
		otkazi() {
			this.karton=[];
			document.getElementById("pKartona").style.display = "none";
			document.getElementById("zPregled").style.display = "none";
			document.getElementById("zkarton").style.display = "none";
			document.getElementById("myForm").style.display = "none";
			document.getElementById("modaldark").style.display = "none";
			document.getElementById("modaldark").style.opacity="0";
        },
		obrisi(id) {
            axios
            .delete("rest/lekari/"+id,id)
            .then(response =>{		
				axios
			    .get('rest/lekari')
			    .then(response => (this.tipovi=response.data));
			})
			.catch(error => {
				alert("Lekar se ne moze obrisati,ima zakazan pregled.");
			});
        },
        prikaziSve(){
            var i,li;
            li = document.getElementsByClassName("filterDiv");
            for (i = 0; i < li.length; i++) {
          	  li[i].style.display = "";
            }
        },
        fjaPretrage() {
          var lista=[0,1,2];
          var listaduz=lista.length;
          var n,li,i;
          li = document.getElementsByClassName("filterDiv");
          for (i = 0; i < li.length; i++) {
        	  li[i].style.display = "";
          }
          for(var z=0; z<lista.length;z++){       	  
	          var input, filter, ul, a, txtValue;
	          n=lista[z];
	          if(n==0){
	        	  input = document.getElementById('myInput1');
	          }else if(n==1){
	        	  input = document.getElementById('myInput2');
	          }else{
	        	  input = document.getElementById('myInput3');
	          }
	          filter = input.value.toUpperCase();
	          li = document.getElementsByClassName("filterDiv");
	
	          for (i = 0; i < li.length; i++) {
	            a = li[i].getElementsByTagName("td")[n];
	            txtValue = a.textContent || a.innerText;
	            if (txtValue.toUpperCase().indexOf(filter) > -1) {
	              if(li[i].style.display != "none"){
	            	  li[i].style.display = "";
	              }
	            } else {
	              li[i].style.display = "none";
	            }
	          }
        	}
        }
	},
	mounted(){
		axios
		.get('rest/login/getConcreteUser/Lekar')
	    .then((response) => {
	    	this.lekar_username=response.data.username;
	    	this.klinika_id=response.data.kc_id;
	    })
	    .catch(response => {
			this.$router.push("/");
		});
		axios
	    .get('rest/pacijent/svi')
	    .then(response => (this.tipovi=response.data));

	},
});