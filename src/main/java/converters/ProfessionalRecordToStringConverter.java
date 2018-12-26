
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ProfessionalRecord;

@Component
@Transactional
public class ProfessionalRecordToStringConverter implements Converter<ProfessionalRecord, String> {

	@Override
	public String convert(final ProfessionalRecord profR) {
		String result;

		if (profR == null)
			result = null;
		else
			result = String.valueOf(profR.getId());

		return result;
	}

}
