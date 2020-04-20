const Klinike = { template: '<klinike></klinike>' }
const Pregledi = {template: '<pregledi></pregledi>'}
const Karton = {template: '<karton></karton>'}
const Profil = {template: '<pacijentProfil></pacijentProfil>'}
const Empty = {template: '<blank></blank>'}

Vue.component('pacijentHome',{
	data: function(){
		return {
			component:"blank"
		}
		
	},
	template: ` 
	<div>
		<div  id="mySidenav" class="sidenav">
	      	<a href = "#klinike"v-on:click = "component = 'klinike'"  >Klinike</a>
			<a href = "#pregledi" v-on:click = "component = 'pregledi'">Pregledi</a>
			<a href = "#karton" v-on:click = "component = 'karton'">Zdravstveni karton</a>
			<a href = "#profil" v-on:click = "component = 'profil'" >Profil</a>
			<div class="align-self-center mx-auto"> 
                <button id = "odjavi" class="btn btn-primary btn-sm">Odjavi se</button>
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
		'klinike': Klinike,
		'pregledi': Pregledi,
		'karton': Karton,
		'profil': Profil,
		'blank':Empty
	},
	
	methods : {
	},
	mounted(){

	}
});