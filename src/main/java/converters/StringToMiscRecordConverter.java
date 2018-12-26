
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.MiscRecordRepository;
import domain.MiscRecord;

@Component
@Transactional
public class StringToMiscRecordConverter implements Converter<String, MiscRecord> {

	@Autowired
	MiscRecordRepository	miscRecordRepository;


	@Override
	public MiscRecord convert(final String text) {
		MiscRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.miscRecordRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
