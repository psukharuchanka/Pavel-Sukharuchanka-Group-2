
$(document).ready(function(){
	
	$('#contactForm').bootstrapValidator({
		container : 'tooltip',
		feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
		fields: {
			fullName: {
                validators: {
                    stringLength: {
						min: 3,
                        message: 'The field name can not be less than 3 characters'                        
                    },
					notEmpty: {
                        message: 'The full name is required and can not be empty'
                    }
                }				
            }
		}
		
	})
	.on('error.field.bv', function(e, data) {
		// Get the tooltip
		var $parent = data.element.parents('.form-group'),
			$icon   = $parent.find('.form-control-feedback[data-bv-icon-for="' + data.field + '"]'),
			title   = $icon.data('bs.tooltip').getTitle();

		// Destroy the old tooltip and create a new one positioned to the right
		$icon.tooltip('destroy').tooltip({
			html: true,
			placement: 'right',
			title: title,
			container: 'body'
		});
	});
	
});

