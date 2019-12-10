<#import "macros/common.ftlh" as c/>

<@c.page title="Hello">

	<h2>${greetingMsg}</h2>

	<div class="row">
		<div class="col-md-12">
			<img class="img-responsive" src="/images/pets.png"/>
		</div>
	</div>

	<br>

	<div class="row">
		<a href="/owners" class="btn btn-default">Find owner</a>
	</div>

</@c.page>