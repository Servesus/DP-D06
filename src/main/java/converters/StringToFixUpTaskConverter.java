
package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.FixUpTaskRepository;
import domain.FixUpTask;

@Component
@Transactional
public class StringToFixUpTaskConverter implements Converter<String, FixUpTask> {

	@Autowired
	FixUpTaskRepository	fixUpTaskRepository;


	@Override
	public FixUpTask convert(final String text) {
		FixUpTask result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.fixUpTaskRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
