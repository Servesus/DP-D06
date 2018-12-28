
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.EndorserRecord;

@Component
@Transactional
public class EndorserRecordToStringConverter implements Converter<EndorserRecord, String> {

	@Override
	public String convert(final EndorserRecord endR) {
		String result;

		if (endR == null)
			result = null;
		else
			result = String.valueOf(endR.getId());

		return result;
	}
}
