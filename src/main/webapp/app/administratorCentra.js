//const Klinika = { template : '<klinika></klinika>' }
const Zahtevi = { template : '<zahtevi></zahtevi>' }

Vue.component('administratorCentra',{
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
			kc_id:null,
			prviPut:null
		}
		
	},
	template: ` 
	<div>
		<div  id="mySidenav" class="sidenav">
		    <a href = "#klinika" v-on:click = "component = 'klinika'" >Klinike</a>
		    <a href = "#adminiKlinika" v-on:click = "component = 'adminiKlinika'" >Administratori klinika</a>
		    <a href = "#adminiKCentara" v-on:click = "component = 'adminiKCentara'" >Administratori klinickih centara</a>
		    <a href = "#sifarnik" v-on:click = "component = 'sifarnik'" >Sifarnik</a>
		    <a href = "#zahtevi" v-on:click = "component = 'zahtevi'" >Zahtevi za registracije</a>
			<a href = "#adminKCProfil" v-on:click = "component = 'adminKCProfil'" >Profil</a>
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
	   <div id="modaldark">
	   <div class="form-popup" id="myForm">
	    <h4>Promenite lozinku</h4>
	    <input type="password" class="psw" v-model="lozinka" placeholder="Lozinka" required>
	    <input type="password" class="psw" v-model="ponovljena" placeholder="Ponovljena lozinka" required>
	    <button type="button" class="btn maal leftbutton" v-on:click="dodajLoz()">Potvrdi</button>
	   </div>
	   </div>
	</div>
`
	, 
	components:{
		//'klinika': Klinika,
		'zahtevi': Zahtevi
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
			    .put('rest/adminKC/izmeni',{
			    	"id":this.id,
			        "ime": this.ime,
			        "prezime": this.prezime,
			        "username":this.username,
			        "password": this.lozinka,
			        "adresa": this.adresa,
			        "grad": this.grad,
			        "drzava": this.drzava,
			        "kc_id":this.kc_id,
			        "prviPut":false
				})
			    .then((response) => {
			    	alert("Uspesno ste izmenili lozinku!");
			    	this.otkazi()
			    });
	    	}
	    	else{
	    		alert("Popunite ispravno sva polja (min 6 karaktera)");
	    	}
        }
	},
	mounted() {
		axios
	    .get('rest/login/getConcreteUser/AdminKC')
	    .then((response) => {
	    	this.id=response.data.id;
	    	this.username = response.data.email;
	    	this.ime = response.data.ime;
	    	this.prezime = response.data.prezime;
	    	this.adresa = response.data.adresa;
	    	this.grad = response.data.grad;
	    	this.drzava = response.data.drzava;		    	
	    	this.brojOsiguranika = response.data.brojOsiguranika;
	    	this.kc_id=response.data.kc_id;
	    	this.prviPut = response.data.prviPut;
			if(this.prviPut==true){
				this.otvori();
			}
	    })
	    .catch(response => {
			this.$router.push("/");
		});
	}
});