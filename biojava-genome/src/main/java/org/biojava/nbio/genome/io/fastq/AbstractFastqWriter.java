/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 */
package org.biojava.nbio.genome.io.fastq;

import java.io.*;
import java.util.Arrays;

/**
 * Abstract writer implementation for FASTQ formatted sequences.
 *
 * @since 3.0.3
 */
abstract class AbstractFastqWriter
	implements FastqWriter
{

	/**
	 * Convert the specified FASTQ formatted sequence if necessary.
	 *
	 * @since 4.2
	 * @param fastq FASTQ formatted sequence to convert, must not be null
	 * @return the specified FASTQ formatted sequence or a new FASTA formatted
	 *    sequence if conversion is necessary
	 */
	protected abstract Fastq convert(final Fastq fastq);

	@Override
	public final <T extends Appendable> T append(final T appendable, final Fastq... fastq) throws IOException
	{
		return append(appendable, Arrays.asList(fastq));
	}

	@Override
	public final <T extends Appendable> T append(final T appendable, final Iterable<Fastq> fastq) throws IOException
	{
		if (appendable == null)
		{
			throw new IllegalArgumentException("appendable must not be null");
		}
		if (fastq == null)
		{
			throw new IllegalArgumentException("fastq must not be null");
		}
		for (Fastq f : fastq)
		{
			if (f != null)
			{
				Fastq converted = convert(f);
				appendable.append("@");
				appendable.append(converted.getDescription());
				appendable.append("\n");
				appendable.append(converted.getSequence());
				appendable.append("\n+\n");
				appendable.append(converted.getQuality());
				appendable.append("\n");
			}
		}
		return appendable;
	}

	@Override
	public final void write(final File file, final Fastq... fastq) throws IOException
	{
		write(file, Arrays.asList(fastq));
	}

	@Override
	public final void write(final File file, final Iterable<Fastq> fastq) throws IOException
	{
		if (file == null)
		{
			throw new IllegalArgumentException("file must not be null");
		}
		if (fastq == null)
		{
			throw new IllegalArgumentException("fastq must not be null");
		}
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            append(writer, fastq);
        }
        // ignore
    }

	@Override
	public final void write(final OutputStream outputStream, final Fastq... fastq) throws IOException
	{
		write(outputStream, Arrays.asList(fastq));
	}

	@Override
	public final void write(final OutputStream outputStream, final Iterable<Fastq> fastq) throws IOException
	{
		if (outputStream == null)
		{
			throw new IllegalArgumentException("outputStream must not be null");
		}
		if (fastq == null)
		{
			throw new IllegalArgumentException("fastq must not be null");
		}
		Writer writer = null;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(outputStream));
			append(writer, fastq);
		}
		finally
		{
			if (writer != null)
			{
				try
				{
					writer.flush();
				}
				catch (IOException e)
				{
					// ignore
				}
			}
		}
	}
}
