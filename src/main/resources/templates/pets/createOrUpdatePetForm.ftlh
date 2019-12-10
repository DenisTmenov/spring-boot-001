<#import "../macros/common.ftlh" as c/>

<@c.page title="Pet">


	<script>
		$(function () {
			$("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
		});
	</script>
	<h2>
        <#if pet?? && pet.new>New </#if> Pet
	</h2>
	<form class="form-horizontal" method="post">
		<input type="hidden" name="id" id="id" value="${pet.id!}"/>
		<div class="form-group has-feedback">
			<div class="form-group">
				<label class="col-sm-2 control-label">Owner</label>
				<div class="col-sm-10">
                    ${pet.owner.firstName!} ${pet.owner.lastName!}
				</div>
			</div>
			<label title="Name" for="name">Pet name</label>
			<input value="${pet.name!}" id="name" name="name" type="text">
			<br/>
			<label title="Birth Date" for="birthDate">Birth Date</label>
			<input value="${(pet.birthDateStr)!""}" id="birthDate" name="birthDateStr" type="date">
			<div class="control-group">
				<label title="Type" for="type">Type</label>
				<select name="type" id="type">
					<option />
                    <#list types as type>
						<option value="${type.id}" title="${type.name}" label="${type.name}"
						<#if pet.type?? && type.id == pet.type.id> selected="selected"</#if>/>
					</#list>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
                <#if pet.new>
					<button class="btn btn-default" type="submit">Add Pet</button>
                <#else >
					<button class="btn btn-default" type="submit">Update Pet</button>
                </#if>
			</div>
		</div>
	</form>
</@c.page>