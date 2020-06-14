//const RadniKalendar = { template : '<radniKalendar></radniKalendar>' }
//const Overavanje = { template : '<overavanje></overavanje>' }

Vue.component("MSHome", {
	data: function(){
		return {
			component:"blank",
			id:null,
			username: "",
            ime: "",
            prezime: "",
            adresa: "",
            grad: "",
            drzava: "",
            lozinka: "",
            ponovljena: "",
            brojOsiguranika: "",
            radnoVremeDo:"",
			radnoVremeOd:"",
			kc_id:null,
		    prviPut:null
		}
		
	},
	template: ` 
	<div>
		<div  id="mySidenav" class="sidenav">
	      	<a href = "#pacijenti" v-on:click = "component = 'pacijenti'" >Pacijenti</a>
	      	<a href = "#radniKalendar" v-on:click = "component = 'radniKalendar'" >Radni kalendar</a>
			<a href = "#godisnjiSlanjeMS" v-on:click = "component = 'godisnjiSlanjeMS'" >Godisnji</a>
			<a href = "#MSProfil" v-on:click = "component = 'MSProfil'" >Profil</a>
			<a href = "#overavanje" v-on:click = "component = 'overavanje'" >Overavanje recepata</a>
			<div class="align-self-center mx-auto"> 
                <button id = "odjavi" class="btn btn-primary btn-sm" v-on:click="odjava()">Odjavi se</button>
            </div> 
			
		</div>
		<!-- /#sidebar-wrapper -->

	    <!-- Page Content -->
	    <div id="page-content-wrapper">
			<component v-bind:is = "component"></component>
	    </div>
	    <!-- /#page-content-wrapper -->
	   <!---div id="modaldark">
	   <div class="form-popup" id="myForm">
	    <h4>Promenite lozinku</h4>
	    <input type="password" class="psw" v-model="lozinka" placeholder="Lozinka" required>
	    <input type="password" class="psw" v-model="ponovljena" placeholder="Ponovljena lozinka" required>
	    <button type="button" class="btn maal leftbutton" v-on:click="dodajLoz()">Potvrdi</button>
	   </div>
	   </div--->
	   	   <!-- Modal -->
<div class="modal fade" id="lozinka" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Promenite lozinku</h5>
      </div>
      <div class="modal-body">
		<form>
			<div class="form-group">
		      	<label for = "lozinka">Lozinka: </label>
	    		<input id="lozinka" type="password" class="psw" v-model="lozinka" placeholder="Lozinka" required>
		    </div>
		    <div class="form-group">
		      	<label for = "lozinka2">Ponovljena lozinka: </label>
	    		<input id="lozinka2" type="password" class="psw" v-model="ponovljena" placeholder="Ponovljena lozinka" required>
		    </div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" v-on:click="dodajLoz()">Potvrdi</button>
      </div>
    </div>
  </div>
</div>
	</div>
`
		, 
		components:{
			//'radniKalendar': RadniKalendar,
			//'overavanje': Overavanje
		},
		
		methods : {
			odjava() {
	        	axios
	        	.get('rest/login/odjava')
				.then(response => this.$router.replace({ name: "login" }));
	        },
			otvori() {
				document.getElementById("myForm").style.display = "block";
				document.getElementById("modaldark").style.display = "block";
				document.getElementById("modaldark").style.opacity="1";
	        },
			otkazi() {
				document.getElementById("myForm").style.display = "none";
				document.getElementById("modaldark").style.display = "none";
				document.getElementById("modaldark").style.opacity="0";
	        },
	        dodajLoz(){
	        	if(this.lozinka==this.ponovljena && this.lozinka.length>5){
					axios
				    .put('rest/medSestre/izmeni',{
				    	"id":this.id,
				        "ime": this.ime,
				        "prezime": this.prezime,
				        "username":this.username,
				        "password": this.lozinka,
				        "adresa": this.adresa,
				        "grad": this.grad,
				        "drzava": this.drzava,
				        "brojOsiguranika":this.brojOsiguranika,
				        "radnoVremeDo":this.radnoVremeDo,
				        "radnoVremeOd":this.radnoVremeOd,
				        "kc_id":this.kc_id,
				        "prviPut":false
					})
				    .then((response) => {
				    	alert("Uspesno ste izmenili lozinku!");
				    	$('#lozinka').modal('hide');
		            	$('.modal-backdrop').remove();				    });
		    	}
		    	else{
		    		alert("Popunite ispravno sva polja (min 6 karaktera)");
		    	}
	        }
		},
		mounted() {
			axios
		    .get('rest/login/getConcreteUser/MedicinskaS')
		    .then((response) => {
		    	this.id=response.data.id;
		    	this.username = response.data.email;
		    	this.ime = response.data.ime;
		    	this.prezime = response.data.prezime;
		    	this.adresa = response.data.adresa;
		    	this.grad = response.data.grad;
		    	this.drzava = response.data.drzava;
		    	this.brojOsiguranika = response.data.brojOsiguranika;
		    	this.radnoVremeDo=response.data.radnoVremeDo;
		    	this.radnoVremeOd=response.data.radnoVremeOd;
		    	this.kc_id=response.data.kc_id;
		    	this.prviPut = response.data.prviPut;
				if(this.prviPut==true){
					$('#lozinka').modal('show');
				}
		    })
		    .catch(response => {
				this.$router.push("/");
			});
		}
});