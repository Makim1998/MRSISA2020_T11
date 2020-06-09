const Klinike = { template: '<klinikePacijent></klinikePacijent>' }
const LekariPacijent = { template: '<lekariPacijent></lekariPacijent>' }
const ZakazivanjePacijent = {template: '<zakazivanje></zakazivanje>'}
const Pregledi = {template: '<pregledi></pregledi>'}
const Karton = {template: '<karton></karton>'}
const Profil = {template: '<pacijentProfil></pacijentProfil>'}
const Empty = {template: '<blank></blank>'}

Vue.component('pacijentHome',{
	data: function(){
		return {
			component:"blank",
			klinika: null,
			lekar: null,
			poruka:{
	    		datum:"",
	    		tip:""
	    	}
		}
		
	},
	template: ` 
	<div id = "pacijentHome">
		<div  id="mySidenav" class="sidenav">
	      	<a href = "#klinike"v-on:click = "component = 'klinike'"  >Klinike</a>
	      	<a href = "#lekari" v-on:click = "component = 'lekari'"  >Lekari</a>
			<a href = "#pregledi" v-on:click = "component = 'pregledici'">Pregledi</a>
			<a href = "#karton" v-on:click = "component = 'karton'">Zdravstveni karton</a>
			<a href = "#profil" v-on:click = "component = 'profil'" >Profil</a>
			<div class="align-self-center mx-auto"> 
                <button id = "odjavi" class="btn btn-primary btn-sm" v-on:click="odjava()">Odjavi se</button>
            </div> 
			
		</div>
		<!-- /#sidebar-wrapper -->
	     <div id="page-content-wrapper" >
	     	<div v-if="component === 'pregledici'">
			  <pregledi  v-on:novi = "novica()"></pregledi>
			</div>
			<div v-else-if="component === 'klinike'">
			  <klinikePacijent  v-on:skok = "javiSe($event)"  v-on:poruka = "porukaa($event)"></klinikePacijent>
			</div>
			<div v-else-if="component === 'lekari'">
			  <lekariPacijent v-on:skok-lekar = "zakazivanje($event)" v-bind:klinika="klinika"></lekariPacijent>
			</div>
			
			<div v-else-if="component === 'profil'">
			  <pacijentProfil></pacijentProfil>
			</div>
			<div v-else-if="component === 'karton'">
			  <karton></karton>
			</div>
			<div v-else-if="component === 'zakazivanjice'">
			  <zakazivanje v-on:stigao= "stigao()" v-on:nazad = "nazad()" v-bind:lekarodabran="lekar"  v-bind:date="poruka.datum" v-bind:type="poruka.tip" v-bind:klinikaodabrana = "klinika"></zakazivanje>
			</div>
			<div v-else>
			  <blank></blank>
			</div>
			
	    </div>
	    <!-- /#page-content-wrapper -->
	    <div class ="footer"></div>
	</div>
`
	, 
	components:{
		'klinike': Klinike,
		'pregledici': Pregledi,
		'karton': Karton,
		'profil': Profil,
		'lekari': LekariPacijent,
		'blank':Empty,
		'zakazivanjice': ZakazivanjePacijent
	},
	
	methods : {
		javiSe(klinika) {
        	console.log("skok")
        	console.log(klinika.naziv);
        	this.klinika = klinika;
        	console.log(this.klinika.naziv);
        	this.component = 'lekari';
        },
        zakazivanje(lekar){
        	console.log("skok zakazivanje");
        	console.log(lekar);
        	console.log(this.poruka.datum);
        	console.log(this.poruka.tip);
        	this.lekar = lekar;
        	console.log(this.lekar.klinika);
        	this.component = 'zakazivanjice';
        },
        novica(n) {
        	console.log(n);
        	this.component = 'zakazivanjice';
        },
        nazad() {
        	console.log("skok nazad")
        	this.component = 'pregledici';
        },
        stigao() {
        	console.log("stigao do zakazivanja")
        	this.poruka.datum = "";
        	this.poruka.tip = "";
        	this.klinika = null;
        	this.lekar = null;
        },
        porukaa(p){
        	console.log("stigla poruka");
        	this.poruka.datum = p.datum;
        	this.poruka.tip = p.tip;
        	console.log(this.poruka.datum);
        	console.log(this.poruka.tip);
        },
		odjava() {
        	axios
        	.get('rest/login/odjava')
			.then(response => this.$router.replace({ name: "login" }));
        }
	},
	mounted() {
		axios
	    .get('rest/login/getConcreteUser/Pacijent')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
	}
});