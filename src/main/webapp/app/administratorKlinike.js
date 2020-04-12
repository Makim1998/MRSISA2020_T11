Vue.component("administratorKlinike", {
	template: ` 
<div>
   <p> Dobrodosli administrator klinike</p>
   <router-link :to="{ name: 'tipPregleda' }" tag="button" >Tip Pregleda</router-link>
   <router-link :to="{ name: 'sala' }" tag="button" >Sala</router-link>
</div>		  
`
	,
});