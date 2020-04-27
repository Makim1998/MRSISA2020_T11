//const Sala = { template : '<sala></sala>' }
//const TipPregleda = { template : '<lekari></godisnji>' }
//const Klinika = { template : '<klinika></klinka>' }
//const Lekari = { template : '<lekari></lekari>' }
const Godisnji = { template : '<godisnji></godisnji>' }
//const TerminPregleda = { template : '<terminPregleda></terminPregleda>' }
const Izvestaji = { template : '<izvestaji></izvestaji>' }
//const ProfilAK = { template : '<adminKProfil></adminKProfil>' }

Vue.component('administratorKlinike',{
	data: function(){
		return {
			component:"blank"
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
	      	<a href = "#godisnji" v-on:click = "component = 'godisnji'" >Godisnji</a>
	      	<a href = "#terminPregleda" v-on:click = "component = 'terminPregleda'" >Termini za pregled</a>
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
	</div>
`
	, 
	components:{
		//'sala': Sala,
		//'tipPregleda': TipPregleda,
		//'klinika': Klinika,
		'godisnji': Godisnji,
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
        }
	},
	mounted() {
		axios
	    .get('rest/login/getConcreteUser/AdminK')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
	}
});