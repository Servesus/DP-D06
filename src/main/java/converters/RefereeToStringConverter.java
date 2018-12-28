
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Referee;

@Component
@Transactional
public class RefereeToStringConverter implements Converter<Referee, String> {

	@Override
	public String convert(final Referee r) {
		String result;

		if (r == null)
			result = null;
		else
			result = String.valueOf(r.getId());

		return result;
	}

}
