Vue.component("lekar", {
	template: ` 
<div>
   <div class="jumbotron">
	  <h2>Lekar</h2>
	  <p>Ime prezime,datum rodjenja.</p> 
   </div>
   <div class="card text-white bg-primary mb-3" style="max-width:25%; min-height:263px; float:left;">
     <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
       <div class="card-body">
         <h5 class="card-title">Pacijenti</h5>
         <p class="card-text">Mozete da pogledate listu svih pacijenata klinike i sortirate ih po odredjenom kriterijumu</p>
       </div>
     </div>
    <div class="card text-white bg-warning mb-3" style="max-width:25%; min-height:263px; float:left;">
     <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg">Klikni</router-link></div>
      <div class="card-body">
         <h5 class="card-title">Pregled</h5>
         <p class="card-text">Mozete da zapocnete pregled i unesete informacije o pregledu</p>
       </div>
     </div>
    <div class="card text-white bg-primary mb-3" style="max-width:25%; min-height:263px; float:left;">
     <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
     <div class="card-body">
        <h5 class="card-title">Radni kalendar </h5>
        <p class="card-text">Mozete da pogledate svoj radni kalendar gde ce vam se prikazati svi vasi pregledi i operacije.</p>
	  </div>
	</div>
	<div class="card text-white bg-secondary mb-3" style="max-width: 25%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Godisnji odmori/odsustva</h5>
	    <p class="card-text">Mozete kreirati zahtev za godisnji odmor/odsustvo.</p>
	  </div>
	</div>
	<div class="card text-white bg-info mb-3" style="max-width: 25%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Zakazivanje pregleda/operacije</h5>
	    <p class="card-text">Omoguceno vam je da zakazete pregled ili operaciju.</p>
	  </div>
	</div>
	<div class="card bg-light mb-3" style="max-width: 25%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Licni podaci</h5>
	    <p class="card-text">Mozete da pogledate svoj profil i azurirate licne podatke.</p>
	  </div>
	</div>
	<div class="card text-white bg-dark mb-3" style="max-width: 25%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Promena lozinke</h5>
	    <p class="card-text">Prvi put kada se logujete morate promeniti lozinku.</p>
	  </div>
	 </div>	
</div>	  
`   
	,
});