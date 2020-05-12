const Klinike = { template: '<klinikePacijent></klinikePacijent>' }
const LekariPacijent = { template: '<lekariPacijent></lekariPacijent>' }

const Pregledi = {template: '<pregledi></pregledi>'}
const Karton = {template: '<karton></karton>'}
const Profil = {template: '<pacijentProfil></pacijentProfil>'}
const Empty = {template: '<blank></blank>'}

Vue.component('pacijentHome',{
	data: function(){
		return {
			component:"blank",
			klinika: null
		}
		
	},
	template: ` 
	<div id = "pacijentHome">
		<div  id="mySidenav" class="sidenav">
	      	<a href = "#klinike"v-on:click = "component = 'klinike'"  >Klinike</a>
	      	<a href = "#lekari" v-on:click = "component = 'lekari'"  >Lekari</a>
			<a href = "#pregledi" v-on:click = "component = 'pregledi'">Pregledi</a>
			<a href = "#karton" v-on:click = "component = 'karton'">Zdravstveni karton</a>
			<a href = "#profil" v-on:click = "component = 'profil'" >Profil</a>
			<div class="align-self-center mx-auto"> 
                <button id = "odjavi" class="btn btn-primary btn-sm" v-on:click="odjava()">Odjavi se</button>
            </div> 
			
		</div>
		<!-- /#sidebar-wrapper -->
	     <div id="page-content-wrapper" >
			<div v-if="component === 'klinike'">
			  <klinikePacijent  v-on:skok = "javiSe($event)" ></klinikePacijent>
			</div>
			<div v-else-if="component === 'lekari'">
			  <lekariPacijent v-bind:klinika="klinika"></lekariPacijent>
			</div>
			<div v-else-if="component === 'pregledi'">
			  <pregledi></pregledi>
			</div>
			<div v-else-if="component === 'profil'">
			  <pacijentProfil></pacijentProfil>
			</div>
			<div v-else-if="component === 'karton'">
			  <karton></karton>
			</div>
			<div v-else>
			  <blank></blank>
			</div>
			
	    </div>
	    <!-- /#page-content-wrapper -->
	</div>
`
	, 
	components:{
		'klinike': Klinike,
		'pregledi': Pregledi,
		'karton': Karton,
		'profil': Profil,
		'lekari': LekariPacijent,
		'blank':Empty
	},
	
	methods : {
		javiSe(klinika) {
        	console.log("skok")
        	console.log(klinika.naziv);
        	this.klinika = klinika;
        	console.log(this.klinika.naziv);
        	this.component = 'lekari';
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