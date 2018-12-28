
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.MiscRecord;

@Component
@Transactional
public class MiscRecordToStringConverter implements Converter<MiscRecord, String> {

	@Override
	public String convert(final MiscRecord miscRecord) {
		String result;

		if (miscRecord == null)
			result = null;
		else
			result = String.valueOf(miscRecord.getId());

		return result;
	}
}
