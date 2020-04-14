Vue.component("lekar", {
	template: ` 
<div>
   <p> Dobrodosli lekar</p>
   
   <router-link :to="{ name: 'homepage' }" tag="button" >Nazad</router-link>
</div>		  
`
	,
});