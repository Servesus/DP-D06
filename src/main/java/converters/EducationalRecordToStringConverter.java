
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.EducationalRecord;

@Component
@Transactional
public class EducationalRecordToStringConverter implements Converter<EducationalRecord, String> {

	@Override
	public String convert(final EducationalRecord edR) {
		String result;

		if (edR == null)
			result = null;
		else
			result = String.valueOf(edR.getId());

		return result;
	}
}
