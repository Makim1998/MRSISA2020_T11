Vue.component("homepage", {
	template: ` 
<div>
   <p> Uspesno logovanje!</p>
   <router-link :to="{ name: 'administratorKlinike' }" tag="button" >Administrator klinike</router-link>
</div>		  `
	,
});