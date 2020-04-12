Vue.component("administratorKlinike", {
	template: ` 
<div>
   <div class="jumbotron">
	  <h2>Administrator klinike</h2>
	  <p>Ime prezime,datum rodjenja.</p> 
   </div>
   <div class="card text-white bg-primary mb-3" style="max-width:20%; min-height:263px; float:left;">
     <div class="card-header"><router-link :to="{ name: 'tipPregleda' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
       <div class="card-body">
         <h5 class="card-title">Tip pregleda</h5>
         <p class="card-text">Mozete da pretrazujete, dodajete, menjate i uklanjate tipove pregleda.</p>
       </div>
     </div>
    <div class="card text-white bg-warning mb-3" style="max-width:20%; min-height:263px; float:left;">
     <div class="card-header"><router-link :to="{ name: 'sala' }" tag="button" class="btn btn-secondary btn-lg">Klikni</router-link></div>
      <div class="card-body">
         <h5 class="card-title">Sala</h5>
         <p class="card-text">Mozete da pretrazujete, dodajete, menjate i uklanjate sale.</p>
       </div>
     </div>
    <div class="card text-white bg-primary mb-3" style="max-width:20%; min-height:263px; float:left;">
     <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
     <div class="card-body">
        <h5 class="card-title">Klinika</h5>
        <p class="card-text">Imate mogucnost da uredjujete profil klinike.</p>
	  </div>
	</div>
	<div class="card text-white bg-secondary mb-3" style="max-width: 20%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Godisnji odmori/odsustva</h5>
	    <p class="card-text">Mozete voditi evidenciju o godisnjim odmorima/odsustvima medicinskog osoblja.</p>
	  </div>
	</div>
	<div class="card text-white bg-success mb-3" style="max-width: 20%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Termini za pregled</h5>
	    <p class="card-text">Imate mogucnost da definisete slobodne termine za preglede koje pacijenti mogu da rezervisu.</p>
	  </div>
	</div>
	<div class="card text-white bg-danger mb-3" style="max-width: 20%; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Zahtevi za pregled/operaciju</h5>
	    <p class="card-text">Za zahtev pacijenta ili lekara za pregled dodeljujete salu, za zahtev lekara za operaciju dodeljujete satnicu i salu.</p>
	  </div>
	</div>
	<div class="card text-white bg-primary mb-3" style="max-width: 20%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Lekari</h5>
	    <p class="card-text">Mozete da pretrazujete, dodajete, menjate i uklanjate lekare.</p>
	  </div>
	</div>
	<div class="card text-white bg-info mb-3" style="max-width: 20%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Izvestaj o poslovanju</h5>
	    <p class="card-text">Omogucen vam je prikaz izvestaja o poslovanju.</p>
	  </div>
	</div>
	<div class="card bg-light mb-3" style="max-width: 20%; min-height:263px; float:left;">
	  <div class="card-header"><router-link :to="{ name: 'homepage' }" tag="button" class="btn btn-secondary btn-lg" >Klikni</router-link></div>
	  <div class="card-body">
	    <h5 class="card-title">Licni podaci</h5>
	    <p class="card-text">Mozete da azurirate licne podatke.</p>
	  </div>
	</div>
	<div class="card text-white bg-dark mb-3" style="max-width: 20%; min-height:263px; float:left;">
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