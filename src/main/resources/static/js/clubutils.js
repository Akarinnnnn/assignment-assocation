function appendClub(container, club) {
	container.append("<div class=\"bg-light rounded-2 mb-1 p-1\"><div class=\"row\">\n" +
		"<div class=\"col-4\">" + club.name + "</div>\n" +
		"<div class=\"col-4\" >" + club.foundDate + "</div>\n" +
		"<div class=\"col-4\">\n" +
		"   <a class=\"btn btn-primary\" data-id=\"" + club.id + "\" onclick=\"goEdit(this)\">编辑</a>\n" +
		"   <button class=\"btn btn-danger\" data-id=\"" + club.id +
		"\" onclick=\"deleteClub(this)\">删除</button>\n" +
		" </div>\n" +
		"</div></div>")
}

async function getOnePageOfClub(numPage) {let response = await fetch(clubapi + "/list/" + numPage, {
		body: '{}',
		method: "GET",
		headers: {
			"Content-Type": 'application/json',
			'X-CSRF-TOKEN': csrfToken
		},
		credentials: "same-origin"
	})

	if(response.ok) {
		let container = $('#search-result');
		container.empty();
		for (const club of response.json()) {
			appendClub(container, club)
		}
	}
};

function searchClubClicked() {
	let se = $("#clubName").val();
	
	if(!se)
		se = "";
	
	$.ajax({
		url: clubapi + "/search/" + encodeURIComponent(se),
		type: "GET",
		dataType: "json",
		success: (data, textStatus) => {
			let container = $('#search-result');
			container.empty();
			for (const club of data) {
				appendClub(container, club)
			}
		}
	});
}