const Pacijenti = { template : '<pacijenti></pacijenti>' }
const Pregled = { template : '<pregled></pregled>' }
const RadniKalendar = { template : '<radniKalendar></radniKalendar>' }
const GodisnjiL = { template : '<godisnjiL></godisnjiL>' }
const Zakazivanje = { template : '<zakazivanje></zakazivanje>' }
//const ProfilL = { template : '<profilL></profilL>' }

Vue.component('lekar',{
	data: function(){
		return {
			component:"blank"
		}
		
	},
	template: ` 
	<div>
		<div  id="mySidenav" class="sidenav">
		    <a href = "#pacijenti" v-on:click = "component = 'pacijenti'" >Pacijenti</a>
	      	<a href = "#pregled" v-on:click = "component = 'pregled'" >Pregled</a>	      	
			<a href = "#radniKalendar" v-on:click = "component = 'radniKalendar'" >Radni kalendar</a>
	      	<a href = "#godisnjiL" v-on:click = "component = 'godisnjiL'" >Godisnji</a>
	      	<a href = "#zakazivanje" v-on:click = "component = 'zakazivanje'" >Zakazivanje</a>
			<a href = "#lekarProfil" v-on:click = "component = 'lekarProfil'" >Profil</a>
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
		'pacijenti': Pacijenti,
		'radniKalendar': RadniKalendar,
		'godisnjiL': GodisnjiL,
		'pregled': Pregled,
		'zakazivanje': Zakazivanje
		//'profilL': ProfilL
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
	    .get('rest/login/getConcreteUser/Lekar')
	    .then((response) => {
	    	console.log(response.data);	
	    })
	    .catch(response => {
			this.$router.push("/");
		});
	}
});