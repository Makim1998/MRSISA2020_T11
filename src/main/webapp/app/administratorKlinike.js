//const Sala = { template : '<sala></sala>' }
//const TipPregleda = { template : '<lekari></godisnji>' }
//const Klinika = { template : '<klinika></klinka>' }
//const Lekari = { template : '<lekari></lekari>' }
//const Godisnji = { template : '<godisnji></godisnji>' }
//const TerminPregleda = { template : '<terminPregleda></terminPregleda>' }
const Izvestaji = { template : '<izvestaji></izvestaji>' }
//const ProfilAK = { template : '<adminKProfil></adminKProfil>' }

Vue.component('administratorKlinike',{
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
		    <a href = "#klinikaProfil" v-on:click = "component = 'klinikaProfil'" >Klinika</a>
	      	<a href = "#lekari" v-on:click = "component = 'lekari'" >Lekari</a>	      	
			<a href = "#tipPregleda" v-on:click = "component = 'tipPregleda'" >TipPregleda</a>
	      	<a href = "#sala" v-on:click = "component = 'sala'" >Sala</a>
	      	<a href = "#cenovnik" v-on:click = "component = 'cenovnik'" >Cenovnik klinike</a>
	      	<a href = "#godisnjiPrihvatanje" v-on:click = "component = 'godisnjiPrihvatanje'" >Godisnji</a>
	      	<a href = "#terminPregleda" v-on:click = "component = 'terminPregleda'" >Termini za pregled</a>
			<a href = "#zahteviPregled" v-on:click = "component = 'zahteviPregled'" >Zahtevi za pregled</a>
			<a href = "#izvestaji" v-on:click = "component = 'izvestaji'">Izvestaji o poslovanju</a>
			<a href = "#adminKProfil" v-on:click = "component = 'adminKProfil'" >Profil</a>
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
		//'sala': Sala,
		//'tipPregleda': TipPregleda,
		//'klinika': Klinika,
		//'godisnji': Godisnji,
		//'terminPregleda': TerminPregleda,
		//'lekari': Lekari,
		'izvestaji': Izvestaji,
		//'profilAK': ProfilAK
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
			    .put('rest/adminK/izmeni',{
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
	    .get('rest/login/getConcreteUser/AdminK')
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