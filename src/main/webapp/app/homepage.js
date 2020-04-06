Vue.component("homepage", {
	template: ` 
<div>
   <p> Uspesno logovanje!</p>
   <router-link :to="{ name: 'tipPregleda' }" tag="button" >Tip pregleda</router-link>
</div>		  
`
	,
});