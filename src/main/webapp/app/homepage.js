Vue.component("homepage", {
	template: ` 
<div>
   <p> Uspesno logovanje!</p>
   <router-link :to="{ name: 'administratorKlinike' }" tag="button" >Administrator klinike</router-link>
   <router-link :to="{ name: 'lekar' }" tag="button" >Lekar</router-link>
</div>		  `
	,
});