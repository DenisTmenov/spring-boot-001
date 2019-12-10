<#import "../macros/common.ftlh" as c/>

<@c.page title="Visit">

	<script>
		$(function () {
			$("#date").datepicker({dateFormat: 'yy/mm/dd'});
		});
	</script>
	<h2><#if visit?? && visit.new>New</#if> Visit</h2>

	<b>Pet</b>
	<table class="table table-striped">
		<thead>
		<tr>
			<th>Name</th>
			<th>Birth Date</th>
			<th>Type</th>
			<th>Owner</th>
		</tr>
		</thead>
		<tr>
			<td>${visit.pet.name}</td>
			<td>${(visit.pet.birthDate).format('yyyy-MM-dd')}</td>
			<td>${visit.pet.type.name}</td>
			<td>${visit.pet.owner.firstName} ${visit.pet.owner.lastName}</td>
		</tr>
	</table>

	<form class="form-horizontal" method="post">
		<div class="form-group has-feedback">
			<label label="Date" for="date">Date</label>
			<input name="date" id="date" type="date" value="${visit.date}"/>
			<br/>
			<label label="description" for="description">Description</label>
			<input name="description" id="description" value="${visit.description!}"/>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="hidden" name="petId" value="${visit.pet.id}"/>
				<button class="btn btn-default" type="submit">Add Visit</button>
			</div>
		</div>
	</form>

	<br/>
	<b>Previous Visits</b>
	<table class="table table-striped">
		<tr>
			<th>Date</th>
			<th>Description</th>
		</tr>
		<#list visit.pet.visits as visitPrev>
			<#if !visitPrev.new>
				<tr>
					<td>
						${(visitPrev.date).format('yyyy-MM-dd')}
					</td>
					<td>
						${visitPrev.description!}
					</td>
				</tr>
            </#if>
        </#list>
	</table>


</@c.page>