<#import "../macros/common.ftlh" as c/>

<@c.page title="Owner">

<h2>Find Owners</h2>

<form action="/owners" method="get" class="form-horizontal"
		   id="search-owner-form">
	<div class="form-group">
		<div class="control-group" id="lastName">
			<label class="col-sm-2 control-label" for="lastName">Last name </label>
			<div class="col-sm-10">
				<input class="form-control" name="lastName" id="lastName" value="${owner.lastName}" size="30" maxlength="80"/>
				<span class="help-inline">${error!}</span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">Find Owner</button>
		</div>
	</div>

</form>

<br/>
<a class="btn btn-default" href='owners/new'>Add Owner</a>
</@c.page>