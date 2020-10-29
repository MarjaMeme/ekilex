var windowWidthTreshold = 768;

$(window).on("popstate", function(e) {
	e.preventDefault();
	var historyState = e.originalEvent.state;
	if (historyState != null) {
		window.location = historyState.wordSelectUrl;
	}
});

//Dismiss popovers when clicked outside of it
$(document).on('click', function(e) {
	$('[data-toggle="popover"],[data-original-title]').each(function() {
		//the 'is' for buttons that trigger popups
		//the 'has' for icons within a button that triggers a popup
		if (!$(this).is(e.target) && $(this).has(e.target).length === 0 && $('.popover').has(e.target).length === 0) {
			if ($(this).data('bs.popover')) {
				(($(this).popover('hide').data('bs.popover') || {}).inState || {}).click = false // fix for BS 3.3.6
			}
		}
	});
});

//Hide tooltips on click on mobile
$(document).on("click", ".btn-ellipsis", function(e) {
	$(this).tooltip('hide');
});

function fetchDetails(wordId, wordSelectUrl) {
	var detailsDiv = $('.word-details');
	var wordDetailsUrlWithParams = wordDetailsUrl + "/" + wordId;

	$.get(wordDetailsUrlWithParams).done(function(data) {
		detailsDiv.replaceWith(data);
		fetchCorpSentences();
		updateBrowserHistory(wordSelectUrl);
		setHomonymNrVisibility();
		$('.word-details [data-toggle="tooltip"], [data-tooltip="tooltip"]').tooltip();
		$('[data-toggle="popover"]').popover({
			placement : 'top'
		});
		$('.scrollable-table').scrollableTable();
		var lightbox = new SimpleLightbox('.gallery-image', {
			history : false,
			captionPosition : 'outside',
			navText : [ '<i class="fas fa-arrow-left"></i>', '<i class="fas fa-arrow-right"></i>' ],
			closeText : '<i class="fas fa-times"></i>'
		});
		$("#mainContentArea").removeClass("loading");
	}).fail(function(data) {
		alert(messages.search_failure);
		$("#mainContentArea").removeClass("loading");
	})
}

function setHomonymNrVisibility() {
	var nrOfHomonyms = $(".homonym-item").length;
	if (nrOfHomonyms === 1) {
		$('.word-details-homonym-nr').addClass('d-none');
	}
}

function fetchCorpSentences() {
	var corpDiv = $("#corp");
	var corpSentencesUrl = corpUrl + '/' + currentWordLang + '/' + encodeURIComponent(currentWord);
	$.get(corpSentencesUrl).done(function(data) {
		corpDiv.replaceWith(data);
		//Activate collapse button after last bit of data is loaded
		activateCollapseBtn();
	}).fail(function(data) {
	})
}

function updateBrowserHistory(wordSelectUrl) {
	if (currentWord.indexOf('/') !== -1) {
		wordSelectUrl = wordSelectUrl.replace(currentWord, encodeURIComponent(currentWord));
	}
	var historyState = {
		wordId : currentWordId,
		word : currentWord,
		wordSelectUrl : wordSelectUrl
	};
	history.pushState(historyState, "Sõnaveeb", wordSelectUrl);
}

$(document).on("click", ".menu-btn", function() {
	if ($(this).attr('aria-expanded') === 'false') {
		$(this).attr('aria-expanded', 'false');
	} else {
		$(this).attr('aria-expanded', 'true');
	}
});

function activateCollapseBtn() {
	$(".btn-collapse").on("click", function() {
		$(this).toggleClass('show');

		if ($(this).data("see-less") && $(this).data("see-more")) {
			let _txt;
			if ($(this).hasClass('show')) {
				_txt = $(this).data("see-less");
			} else {
				_txt = $(this).data("see-more");
			}

			$(this).find('.btn-txt').text(_txt);
		}
		if ($(this).data("dynamic-text")) {
			$(this).find('.btn-content').toggleClass('d-none');
			$(this).find('.see-more-content').tooltip('hide');
		}
		if ($(this).data("toggle-container")) {
			$($(this).data("toggle-container")).toggleClass("expanded");
		}
	});
}

$(document).on("click", ".show-more", function() {

	// button behaviour
	let buttonText = '';
	if ($(this).attr('aria-expanded') === 'false') {
		$(this).attr('aria-expanded', 'true');
		$(this).find("i").removeClass("fa-angle-down").addClass("fa-angle-up");
		buttonText = $(this).data("see-less");
	} else {
		$(this).attr('aria-expanded', 'false');
		$(this).find("i").removeClass("fa-angle-up").addClass("fa-angle-down");
		buttonText = $(this).data("see-more");
	}
	$(this).find("span").text(buttonText);

	if ($(this).data("target") !== undefined) {
		$($(this).data("target")).toggleClass('collapse');
	} else {
		// TODO: Refactor all of this selector mess
		$(this).parents(".dependencies, .collocations-section, .corp-panel").toggleClass("expand");
	}
});

$(document).on("click", "button[name='expand-btn']", function() {
	$(this).parent().find(".collapsable[data-collapse='true']").fadeToggle("slow", "linear");
});

$(document).on("click", "a[id^='word-details-link']", function() {
	var wordWrapperForm = $(this).closest("form");
	var wordId = wordWrapperForm.children("[name='word-id']").val();
	var wordSelectUrl = wordWrapperForm.children("[name='word-select-url']").val();
	fetchDetails(wordId, wordSelectUrl);
});

$(document).on("click", ".homonym-item", function() {
	$(".homonym-list-item").removeClass("selected last-selected");
	$(".homonym-item:first").removeClass("animation-target").dequeue();
	$(this).parents(".homonym-list-item").addClass("selected last-selected");
	var homonymList = $('.homonym-list');
	if ($(window).width() >= windowWidthTreshold) {
		homonymList.animate({
			scrollLeft : $('.homonym-list-item.selected .homonym-item-wrap').parent().position().left - $('.search-panel').offset().left
					+ homonymList.scrollLeft()
		}, 200);
	}
	setSelectedHomonymValueForMobile($(this).html());
	$(".homonym-list").removeClass("expand");
});

$(document).on("click", "[name='word-form-btn']", function() {
	var word = $(this).data('word');
	$("input[name = 'searchWord']").val(word);
	$('#search-btn').trigger('click');
});
